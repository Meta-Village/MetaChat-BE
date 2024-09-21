package com.ohgiraffers.metachatbe.meeting.command.application.dto;

import com.ohgiraffers.metachatbe.meeting.command.domain.model.Meeting;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "미팅 데이터", description = "미팅 관련 정보를 담고 있는 데이터 전송 객체")
public class MeetingDTO {

    @Schema(name = "미팅 ID", description = "미팅의 고유 ID", example = "123")
    private long meetingId;

    @Schema(name = "미팅 시작 시간", description = "미팅이 시작된 시간", example = "2023-09-06T10:00:00")
    private LocalDateTime meetStartTime;

    @Schema(name = "미팅 종료 시간", description = "미팅이 종료된 시간", example = "2023-09-06T11:00:00")
    private LocalDateTime meetEndTime;

    @Schema(name = "존 이름", description = "미팅이 진행된 존의 이름", example = "MainZone")
    private ZoneName zoneName;

    @Schema(name = "월드 ID", description = "미팅이 진행된 월드의 고유 ID", example = "456")
    private long worldId;

    public Meeting toEntity() {
        return new Meeting(
                this.meetStartTime,
                this.meetEndTime,
                this.zoneName,
                this.worldId
        );
    }

    @Override
    public String toString() {
        return "MeetingDTO{" +
                "meetingId=" + meetingId +
                ", meetStartTime=" + meetStartTime +
                ", meetEndTime=" + meetEndTime +
                ", zoneName=" + zoneName +
                ", worldId=" + worldId +
                '}';
    }
}
