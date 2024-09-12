package com.ohgiraffers.metachatbe.zonehistory.command.application.dto;

import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import com.ohgiraffers.metachatbe.zonehistory.command.domain.model.ZoneHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="구역 기록 데이터",description = "구역에 대한 출입 기록을 담은 데이터 전송 객체")
public class ZoneHistoryDTO {

    @Schema(description = "사용자가 구역에 입장한 시간", example = "2023-09-06T10:00:00")
    private LocalDateTime entryTime;

    @Schema(description = "사용자가 구역에서 퇴장한 시간", example = "2023-09-06T11:00:00")
    private LocalDateTime existTime;

    @Schema(description = "사용자가 출입한 존의 이름", example = "MainZone")
    private ZoneName zoneName;

    @Schema(description = "해당 출입 기록을 가진 사용자의 ID", example = "user123")
    private String userId;

    @Schema(description = "사용자가 속한 월드의 ID", example = "456")
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
