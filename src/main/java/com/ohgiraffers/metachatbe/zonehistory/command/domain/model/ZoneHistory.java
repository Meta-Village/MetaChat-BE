package com.ohgiraffers.metachatbe.zonehistory.command.domain.model;

import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
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

    @Column(name = "user_id")
    private long userId;

    @Column(name = "world_id")
    private long worldId;

    public ZoneHistory(LocalDateTime entryTime, LocalDateTime existTime, ZoneName zoneName, long userId, long worldId) {
        this.entryTime = entryTime;
        this.existTime = existTime;
        this.zoneName = zoneName;
        this.userId = userId;
        this.worldId = worldId;
    }

    @Override
    public String toString() {
        return "ZoneHistory{" +
                "zoneHistoryId=" + zoneHistoryId +
                ", entryTime=" + entryTime +
                ", existTime=" + existTime +
                ", zoneName=" + zoneName +
                ", userId=" + userId +
                ", worldId=" + worldId +
                '}';
    }
}
