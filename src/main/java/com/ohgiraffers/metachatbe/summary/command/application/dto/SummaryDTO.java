package com.ohgiraffers.metachatbe.summary.command.application.dto;


import com.ohgiraffers.metachatbe.summary.command.domain.model.Summary;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "요약 기록", description = "회의 id 요약 내용")
public class SummaryDTO {

    private Long meetingId;

    private String messages;

    public SummaryDTO() {
    }

    public SummaryDTO(Long meetingId, String messages) {
        this.meetingId = meetingId;
        this.messages = messages;
    }

    public SummaryDTO(Summary messages) {
        this.meetingId = messages.getMeetingId();
        this.messages = messages.getSummary();
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "SummaryDTO{" +
                "summaryId=" + meetingId +
                ", summary='" + messages + '\'' +
                '}';
    }
}
