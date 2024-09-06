package com.ohgiraffers.metachatbe.summary.command.application.controller;

import com.ohgiraffers.metachatbe.file.MinioService;
import com.ohgiraffers.metachatbe.summary.command.application.service.AiCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
public class AiCommunicationController {
//    @Autowired
    private AiCommunicationService myService;

//    @Autowired
    private MinioService minioService;

    public AiCommunicationController(AiCommunicationService myService, MinioService minioService) {
        this.myService = myService;
        this.minioService = minioService;
    }

    //    @PostMapping("/upload")
//    public String uploadFile(@ModelAttribute UploadRequestDto uploadRequest) {
//        try {
//            // DTO에서 meetingId와 파일을 가져옴
//            Long meetingId = uploadRequest.getMeetingId();
//            MultipartFile file = uploadRequest.getFile();
//
//            // MinIO에 파일 업로드
//            String fileUrl = minioService.uploadFile(file);
//
//            // 여기에서 meetingId와 연관된 추가 로직을 처리할 수 있음
//            return "파일이 업로드되었습니다: " + fileUrl + ", 회의 ID: " + meetingId;
//        } catch (Exception e) {
//            return "파일 업로드 실패: " + e.getMessage();
//        }
//    }


    @Deprecated
    @GetMapping("/sendGetRequest")
    public Mono<String> sendGetRequest() {
        return myService.sendGetRequest()
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }


    @Deprecated
    @GetMapping("/sendPostRequest")
    public Mono<String> sendPostRequest() {
        return myService.sendPostRequest()
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }
}
