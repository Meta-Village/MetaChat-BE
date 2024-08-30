package com.ohgiraffers.metachatbe.file.command.application.controller;

import com.ohgiraffers.metachatbe.file.command.application.service.MinioService;
import io.minio.errors.MinioException;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    @Autowired
    private MinioService minioService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public Mono<ResponseEntity<String>> uploadFile(
            @RequestPart("file") Mono<FilePart> filePartMono) {
        return filePartMono
                .flatMap(filePart -> {
                    System.out.println(filePart.filename());
                    return minioService.uploadFile(filePart);  // MinioService에서 Mono<String>을 반환하도록 수정 필요
                })
                .map(fileName -> ResponseEntity.ok("File uploaded successfully"))
                .onErrorResume(e -> {
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Failed to upload file"));
                });
    }

    @GetMapping("/url")
    public Mono<ResponseEntity<String>> getFileUrl(@RequestParam("filename") String filename) {
        return minioService.doesFileExist(filename)
                .flatMap(exists -> {
                    if (exists) {
                        return minioService.getFileUrl(filename)
                                .map(url -> ResponseEntity.ok(url));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found."));
                    }
                })
                .onErrorResume(MinioException.class, e -> {
                    String errorMessage = "Error accessing MinIO: " + e.getMessage();
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage));
                })
                .onErrorResume(Exception.class, e -> {
                    String errorMessage = "Error generating file URL: " + e.getMessage();
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage));
                });
    }
}
