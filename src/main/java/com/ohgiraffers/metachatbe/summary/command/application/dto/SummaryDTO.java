package com.ohgiraffers.metachatbe.summary.command.application.dto;


public class SummaryDTO {

    private Long summaryId;

    private String summary;

    public SummaryDTO() {
    }

    public SummaryDTO(Long summaryId, String summary) {
        this.summaryId = summaryId;
        this.summary = summary;
    }

    public Long getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Long summaryId) {
        this.summaryId = summaryId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "SummaryDTO{" +
                "summaryId=" + summaryId +
                ", summary='" + summary + '\'' +
                '}';
    }
}
