package com.ohgiraffers.metachatbe.world.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "월드 요청 데이터", description = "월드 생성을 위한 요청 데이터를 포함하는 객체")
public class WorldRequestDTO {

    @Schema(description = "월드의 이름", example = "MyWorld")
    private String worldName;

    @Schema( description = "월드의 비밀번호", example = "password123")
    private String worldPassword;

    // 기본 생성자
    public WorldRequestDTO() {
    }

    // 매개변수를 받는 생성자
    public WorldRequestDTO(String worldName, String worldPassword) {
        this.worldName = worldName;
        this.worldPassword = worldPassword;
    }

    // Getters and Setters
    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public String getWorldPassword() {
        return worldPassword;
    }

    public void setWorldPassword(String worldPassword) {
        this.worldPassword = worldPassword;
    }

    @Override
    public String toString() {
        return "WorldRequestDTO{" +
                "worldName='" + worldName + '\'' +
                ", worldPassword='" + worldPassword + '\'' +
                '}';
    }
}
