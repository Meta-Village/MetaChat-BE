package com.ohgiraffers.metachatbe.summary.command.application.controller;

import com.ohgiraffers.metachatbe.summary.command.application.service.AiCommunicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "AI 통신 관리", description = "AI와의 통신을 처리하는 API입니다.")
public class AiCommunicationController {

    @Autowired
    private AiCommunicationService myService;

    @Operation(summary = "GET 요청 전송", description = "AI 서비스에 GET 요청을 보내고 응답을 받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GET 요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    })
    @GetMapping("/sendGetRequest")
    public Mono<String> sendGetRequest() {
        return myService.sendGetRequest()
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }

    @Operation(summary = "POST 요청 전송", description = "AI 서비스에 POST 요청을 보내고 응답을 받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "POST 요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    })
    @GetMapping("/sendPostRequest")
    public Mono<String> sendPostRequest() {
        return myService.sendPostRequest()
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }
}
