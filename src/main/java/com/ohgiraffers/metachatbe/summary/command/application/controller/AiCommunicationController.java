package com.ohgiraffers.metachatbe.summary.command.application.controller;

import com.ohgiraffers.metachatbe.summary.command.application.dto.SummaryDTO;
import com.ohgiraffers.metachatbe.summary.command.application.service.AiCommunicationService;
import com.ohgiraffers.metachatbe.util.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RestController
@Tag(name = "AI 통신 관리", description = "AI와의 통신을 처리하는 API입니다.")
public class AiCommunicationController {

    @Autowired
    private AiCommunicationService aiCommunicationService;

//    //    @Autowired
//    private MinioService minioService;
//
//    public AiCommunicationController(AiCommunicationService myService, MinioService minioService) {
//        this.myService = myService;
//        this.minioService = minioService;
//    }
//
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


    @GetMapping("/sendGetRequest")
    public Mono<String> sendGetRequest() {
        return aiCommunicationService.sendGetRequest()
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }

    @GetMapping("/sendPostRequest")
    public Mono<String> sendPostRequest() {
        return aiCommunicationService.sendPostRequest()
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }

//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "voice 가 전송되었습니다. 요약을 반환합니다."),
//            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
//    })
    @PostMapping(value = "/api/voice"/*, produces = "application/json; charset=UTF-8"*/)
    public CompletableFuture<Mono<SummaryDTO>> sendVoiceToSummary(@RequestBody SummaryDTO summaryDTO) {
        return aiCommunicationService.sendVoiceToAI(summaryDTO);
    }

    @Operation(summary = "회의 요약 조회", description = "ID로 DB에 저장된 요약을 가져옵니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요약을 가져오는데 성공하였습니다."),
            @ApiResponse(responseCode = "400", description = "요약을 찾을 수 없거나 에러가 발생하였습니다.")
    })
    @GetMapping(value = "/api/summary/{meetingId}")
    public ResponseEntity<ResponseMessage> findSummary(@PathVariable Long meetingId) {
        try {
            SummaryDTO summaryDTO = aiCommunicationService.findSummary(meetingId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("요약을 가져오는데 성공하였습니다.", summaryDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }



    @Operation(summary = "회의 요약 입력", description = "DB에 수동으로 요약을 저장합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요약을 성공적으로 저장하였습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @PostMapping(value = "/api/summary")
    public ResponseEntity<ResponseMessage> findSummary(@RequestBody SummaryDTO summaryDTO) {
        try {
            aiCommunicationService.saveSummary(summaryDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("요약을 성공적으로 저장하였습니다."));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }
}
