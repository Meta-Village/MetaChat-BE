package com.ohgiraffers.metachatbe.world.command.application.dto;

import lombok.Data;

@Data
public class WorldResponseDTO {
    private Long wordId;
    private String worldName;

    // 기본 생성자
    public WorldResponseDTO() {
    }

    // 매개변수를 받는 생성자
    public WorldResponseDTO(Long wordId, String worldName) {
        this.wordId = wordId;
        this.worldName = worldName;

    }

}
