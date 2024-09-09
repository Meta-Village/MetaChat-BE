package com.ohgiraffers.metachatbe.summary.command.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Summary {

    @Id
    @Column(unique = true, nullable = false)
    private Long meetingId;


    @Column
    @Size(max = 65535)
    private String summary;

    public Summary() {
    }

    public Summary(Long meetingId, String summary) {
        this.meetingId = meetingId;
        this.summary = summary;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "summaryId=" + meetingId +
                ", summary='" + summary + '\'' +
                '}';
    }
}
