package com.ohgiraffers.metachatbe.world.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "월드 응답 데이터", description = "월드 관련 응답 데이터를 포함하는 객체")
public class WorldResponseDTO {

    @Schema( description = "월드의 고유 ID", example = "1")
    private Long worldId;

    @Schema( description = "월드의 이름", example = "MyWorld")
    private String worldName;

    // 기본 생성자
    public WorldResponseDTO() {
    }

    // 매개변수를 받는 생성자
    public WorldResponseDTO(Long worldId, String worldName) {
        this.worldId = worldId;
        this.worldName = worldName;
    }
}
