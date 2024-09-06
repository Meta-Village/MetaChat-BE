package com.ohgiraffers.metachatbe.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RestController
@Tag(name = "테스트 API", description = "테스트를 위한 간단한 GET 및 POST API")
public class TestController {

    @Operation(summary = "GET 테스트", description = "사용자가 GET 요청을 보낼 수 있는 테스트 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 테스트 요청을 받았습니다."),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다.")
    })
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @Operation(summary = "POST 테스트", description = "사용자가 POST 요청을 보낼 수 있는 테스트 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 POST 테스트 요청을 받았습니다."),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다.")
    })
    @PostMapping("/test")
    public String test2() {
        return "test";
    }
}
