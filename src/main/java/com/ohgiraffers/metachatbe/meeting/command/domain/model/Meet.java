package com.ohgiraffers.metachatbe.meeting.command.domain.model;

import com.ohgiraffers.metachatbe.meeting.command.domain.model.embedded.World;
import com.ohgiraffers.metachatbe.meeting.command.domain.model.embedded.Zone;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_meet")
public class Meet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meet_id")
    private long meetId;

    @Column(name = "meet_start_time")
    private LocalDateTime meetStartTime;

    @Column(name = "meet_end_time")
    private LocalDateTime meetEndTime;

    @Embedded
    private World world;

    @Embedded
    private Zone zone;

    public Meet(LocalDateTime meetStartTime, LocalDateTime meetEndTime, World world, Zone zone) {
        this.meetStartTime = meetStartTime;
        this.meetEndTime = meetEndTime;
        this.world = world;
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "Meet{" +
                "meetId=" + meetId +
                ", meetStartTime=" + meetStartTime +
                ", meetEndTime=" + meetEndTime +
                ", world=" + world +
                ", zone=" + zone +
                '}';
    }
}
