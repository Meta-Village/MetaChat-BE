package com.ohgiraffers.metachatbe.util;

import lombok.Getter;
import lombok.Setter;

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
}