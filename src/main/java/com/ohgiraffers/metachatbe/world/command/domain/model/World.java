package com.ohgiraffers.metachatbe.world.command.domain.model;


import jakarta.persistence.*;

@Entity
@Table(name = "WORLD")
public class World {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "WORLD_NAME", nullable = false)
    private String worldName;

    @Column(name = "WORLD_PASSWORD", nullable = false)
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
