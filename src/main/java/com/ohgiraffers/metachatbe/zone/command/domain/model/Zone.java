package com.ohgiraffers.metachatbe.zone.command.domain.model;

import com.ohgiraffers.metachatbe.zone.command.domain.model.embedded.ZoneName;
import com.ohgiraffers.metachatbe.zone.command.domain.model.embedded.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private long zoneId;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "exist_time")
    private LocalDateTime existTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "zone_name")
    private ZoneName zoneName;

    @Embedded
    private User user;

    public Zone(LocalDateTime entryTime, LocalDateTime existTime, ZoneName zoneName, User user) {
        this.entryTime = entryTime;
        this.existTime = existTime;
        this.zoneName = zoneName;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "zoneId=" + zoneId +
                ", entryTime=" + entryTime +
                ", existTime=" + existTime +
                ", zoneName=" + zoneName +
                ", user=" + user +
                '}';
    }
}
