package com.ohgiraffers.metachatbe.meeting.command.domain.model;

import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_id")
    private long meetId;

    @Column(name = "meeting_start_time")
    private LocalDateTime meetStartTime;

    @Column(name = "meeting_end_time")
    private LocalDateTime meetEndTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "zone_name")
    private ZoneName zoneName;

    @Column(name = "world_id")
    private long worldId;

    public Meeting(LocalDateTime meetStartTime, LocalDateTime meetEndTime, ZoneName zoneName, long worldId) {
        this.meetStartTime = meetStartTime;
        this.meetEndTime = meetEndTime;
        this.zoneName = zoneName;
        this.worldId = worldId;
    }

    public void update(LocalDateTime meetStartTime, LocalDateTime meetEndTime, ZoneName zoneName, long worldId) {
        this.meetStartTime = meetStartTime;
        this.meetEndTime = meetEndTime;
        this.zoneName = zoneName;
        this.worldId = worldId;
    }

    public void end(LocalDateTime endTime) {
        this.meetEndTime = endTime;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetId=" + meetId +
                ", meetStartTime=" + meetStartTime +
                ", meetEndTime=" + meetEndTime +
                ", zoneName=" + zoneName +
                ", worldId=" + worldId +
                '}';
    }
}
