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
    private long userId;
    private long worldId;

    public ZoneHistory toEntity() {
        return new ZoneHistory(
                this.entryTime,
                this.existTime,
                this.zoneName,
                this.userId,
                this.worldId
        );
    }
}