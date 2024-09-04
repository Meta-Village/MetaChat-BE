package com.ohgiraffers.metachatbe.meeting.command.application.dto;

import com.ohgiraffers.metachatbe.meeting.command.domain.model.Meeting;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {
    private long meetingId;
    private LocalDateTime meetStartTime;
    private LocalDateTime meetEndTime;
    private ZoneName zoneName;
    private long worldId;

    public Meeting toEntity() {
        return new Meeting(
                this.meetStartTime,
                this.meetEndTime,
                this.zoneName,
                this.worldId
        );
    }
}