package com.ohgiraffers.metachatbe.summary.command.application.service;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WavFileService {


    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

//    // 파일 업로드
//    public String uploadFile(MultipartFile file) throws Exception {
//        try {
//            String objectName = file.getOriginalFilename();
//
//            minioClient.putObject(
//                    PutObjectArgs.builder()
//                            .bucket(bucketName)
//                            .object(objectName)
//                            .stream(file.getInputStream(), file.getSize(), -1)
//                            .contentType(file.getContentType())
//                            .build());
//
//            return minioClient.getObjectUrl(bucketName, objectName); // 업로드된 파일의 URL 반환
//        } catch (MinioException e) {
//            throw new Exception("MinIO 파일 업로드 오류: " + e.getMessage());
//        }
//    }

//    // 파일 다운로드
//    public InputStream downloadFile(String objectName) throws Exception {
//        try {
//            return minioClient.getObject(
//                    GetObjectArgs.builder()
//                            .bucket(bucketName)
//                            .object(objectName)
//                            .build());
//        } catch (MinioException e) {
//            throw new Exception("MinIO 파일 다운로드 오류: " + e.getMessage());
//        }
//    }

}
