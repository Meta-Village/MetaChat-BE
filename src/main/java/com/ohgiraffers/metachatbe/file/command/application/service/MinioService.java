package com.ohgiraffers.metachatbe.file.command.application.service;

import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    private static final char separator = '_';

    public Mono<Boolean> doesFileExist(String filename) {
        return Mono.fromCallable(() -> {
            try {
                minioClient.statObject(
                        StatObjectArgs.builder()
                                .bucket(bucketName)
                                .object(filename)
                                .build()
                );
                return true;
            } catch (ErrorResponseException e) {
                // 파일이 존재하지 않을 때
                return false;
            }
        });
    }

    public Mono<String> uploadFile(FilePart filePart) {
        return Mono.fromCallable(() -> {
            StringBuilder sb = new StringBuilder();
            sb.append(UUID.randomUUID());
            sb.append(separator);
            sb.append(filePart.filename());

            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // 임시 파일 경로 설정
            Path tempFile = Files.createTempFile("upload-", filePart.filename());

            // FilePart를 임시 파일로 저장
            return filePart.transferTo(tempFile)
                    .then(Mono.fromCallable(() -> {
                        // 파일을 MinIO로 업로드
                        minioClient.putObject(
                                PutObjectArgs.builder().bucket(bucketName).object(sb.toString()).stream(
                                                Files.newInputStream(tempFile), Files.size(tempFile), -1)
                                        .contentType(Files.probeContentType(tempFile))
                                        .build());
                        // 임시 파일 삭제
                        Files.delete(tempFile);

                        return sb.toString();
                    }));
        }).flatMap(mono -> mono);
    }

    public Mono<String> getFileUrl(String filename) {
        return Mono.fromCallable(() -> {
            try {
                // 객체가 존재하는지 확인
                minioClient.statObject(
                        StatObjectArgs.builder()
                                .bucket(bucketName)
                                .object(filename)
                                .build()
                );

                // 객체가 존재하면 프리사인드 URL 생성
                return minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket(bucketName)
                                .object(filename)
                                .expiry(24 * 60 * 60) // 24시간 유효
                                .build()
                );
            } catch (ErrorResponseException e) {
                // MinIO 관련 오류 처리
                throw new RuntimeException("Error accessing MinIO: " + e.getMessage());
            } catch (Exception e) {
                // 기타 예외 처리
                throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
            }
        });
    }
}
