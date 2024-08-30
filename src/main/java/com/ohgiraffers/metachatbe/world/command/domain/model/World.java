package com.ohgiraffers.metachatbe.world.command.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("WORLD")
public class World {

    @Id
    @Column("ID")
    private Long id;

    @Column("WORLD_NAME")
    private String worldName;

    @Column("WORLD_PASSWORD")
    private String worldPassword;

    public World() {
    }

    public World(String worldName, String worldPassword) {
        this.worldName = worldName;
        this.worldPassword = worldPassword;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "World{" +
                "id=" + id +
                ", worldName='" + worldName + '\'' +
                ", worldPassword='" + worldPassword + '\'' +
                '}';
    }
}
