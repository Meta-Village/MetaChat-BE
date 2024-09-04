package com.ohgiraffers.metachatbe.zonehistory.command.application.dto;

import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import com.ohgiraffers.metachatbe.zonehistory.command.domain.model.ZoneHistory;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZoneHistoryDTO {
    private LocalDateTime entryTime;
    private LocalDateTime existTime;
    private ZoneName zoneName;
    private String userId;
    private long worldId;

    public ZoneHistoryDTO(long zoneHistoryId, LocalDateTime entryTime, LocalDateTime existTime, ZoneName zoneName, String userId, long worldId) {
        this.entryTime = entryTime;
        this.existTime = existTime;
        this.zoneName = zoneName;
        this.userId = userId;
        this.worldId = worldId;
    }

    public ZoneHistory toEntity() {
        return new ZoneHistory(
                this.entryTime,
                this.existTime,
                this.zoneName,
                this.userId,
                this.worldId
        );
    }
    public static ZoneHistoryDTO fromEntity(ZoneHistory entity) {
        return new ZoneHistoryDTO(
                entity.getZoneHistoryId(),
                entity.getEntryTime(),
                entity.getExistTime(),
                entity.getZoneName(),
                entity.getUserId(),
                entity.getWorldId()
        );
    }
}