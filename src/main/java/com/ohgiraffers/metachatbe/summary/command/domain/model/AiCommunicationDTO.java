package com.ohgiraffers.metachatbe.summary.command.domain.model;

public class AiCommunicationDTO {

    String minutes;

    public AiCommunicationDTO() {
    }

    public AiCommunicationDTO(String minutes) {
        this.minutes = minutes;
    }

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
