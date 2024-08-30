package com.ohgiraffers.metachatbe.world.command.application.dto;


public class WorldRequestDTO {

    private String worldName;
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
        return "WorldDTO{" +
                "worldName='" + worldName + '\'' +
                ", worldPassword='" + worldPassword + '\'' +
                '}';
    }
}
