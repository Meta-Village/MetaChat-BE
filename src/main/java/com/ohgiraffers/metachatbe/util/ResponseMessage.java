package com.ohgiraffers.metachatbe.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseMessage {
    private int status;
    private String message;
    private Object data;

    public ResponseMessage(int status) {
        this.status = status;
    }

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