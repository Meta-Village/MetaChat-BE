package com.ohgiraffers.metachatbe.summary.command.domain.model;

import jakarta.persistence.*;

@Entity
public class Summary {

    @Id
    @Column(unique = true, nullable = false)
    private Long summaryId;

    @Column
    private String summary;

    public Summary() {
    }

    public Summary(Long summaryId, String summary) {
        this.summaryId = summaryId;
        this.summary = summary;
    }

    public Long getSummaryId() {
        return summaryId;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "summaryId=" + summaryId +
                ", summary='" + summary + '\'' +
                '}';
    }
}
