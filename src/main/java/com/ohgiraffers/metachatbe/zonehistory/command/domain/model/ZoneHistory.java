package com.ohgiraffers.metachatbe.zonehistory.command.domain.model;

import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import com.ohgiraffers.metachatbe.zonehistory.command.domain.model.embedded.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_zone_history")
public class ZoneHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_history_id")
    private long zoneHistoryId;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "exist_time")
    private LocalDateTime existTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "zone_name")
    private ZoneName zoneName;

    @Embedded
    private User user;

    public ZoneHistory(LocalDateTime entryTime, LocalDateTime existTime, ZoneName zoneName, User user) {
        this.entryTime = entryTime;
        this.existTime = existTime;
        this.zoneName = zoneName;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "zoneHistoryId=" + zoneHistoryId +
                ", entryTime=" + entryTime +
                ", existTime=" + existTime +
                ", zoneName=" + zoneName +
                ", user=" + user +
                '}';
    }
}
