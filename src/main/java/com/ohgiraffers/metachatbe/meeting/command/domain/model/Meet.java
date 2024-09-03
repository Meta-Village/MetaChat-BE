package com.ohgiraffers.metachatbe.meeting.command.domain.model;

import com.ohgiraffers.metachatbe.meeting.command.domain.model.embedded.World;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
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

    @Column(name = "zone_name")
    private ZoneName zoneName;

    @Embedded
    private World world;

    public Meet(LocalDateTime meetStartTime, LocalDateTime meetEndTime, ZoneName zoneName, World world) {
        this.meetStartTime = meetStartTime;
        this.meetEndTime = meetEndTime;
        this.zoneName = zoneName;
        this.world = world;
    }

    @Override
    public String toString() {
        return "Meet{" +
                "meetId=" + meetId +
                ", meetStartTime=" + meetStartTime +
                ", meetEndTime=" + meetEndTime +
                ", zoneName=" + zoneName +
                ", world=" + world +
                '}';
    }
}
