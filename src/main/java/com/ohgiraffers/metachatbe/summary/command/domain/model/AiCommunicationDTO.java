package com.ohgiraffers.metachatbe.summary.command.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AI 통신 데이터", description = "AI 통신과 관련된 데이터를 전달하는 객체")
public class AiCommunicationDTO {

    @Schema(name = "회의록", description = "AI가 처리할 회의록 내용", example = "회의 내용 요약입니다.")
    String minutes;

    // 기본 생성자
    public AiCommunicationDTO() {
    }

    // 매개변수를 받는 생성자
    public AiCommunicationDTO(String minutes) {
        this.minutes = minutes;
    }

    // Getters and Setters
    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "AiCommunicationDTO{" +
                "minutes='" + minutes + '\'' +
                '}';
    }
}
