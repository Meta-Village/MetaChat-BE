package com.ohgiraffers.metachatbe.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Schema(name = "응답 메시지", description = "API 호출에 대한 응답 메시지 객체")
public class ResponseMessage {

    @Schema(description = "응답 상태 코드", example = "200")
    private int status;

    @Schema(description = "응답 메시지", example = "요청이 성공적으로 처리되었습니다.")
    private String message;

    @Schema(description = "응답 데이터", example = "사용자 데이터, 목록 등")
    private Object data;

    // 기본 상태 코드 생성자
    public ResponseMessage(int status) {
        this.status = status;
    }

    // 메시지 생성자 (기본 상태 코드는 200 OK)
    public ResponseMessage(String message) {
        this.status = HttpStatus.OK.value(); // Default to 200 OK
        this.message = message;
    }

    // 상태 코드와 메시지만 있는 생성자
    public ResponseMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // 상태 코드, 메시지, 데이터를 모두 포함하는 생성자
    public ResponseMessage(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // 메시지와 데이터만 받는 생성자 (상태 코드는 200 OK로 자동 설정)
    public ResponseMessage(String message, Object data) {
        this.status = HttpStatus.OK.value();
        this.message = message;
        this.data = data;
    }
}
