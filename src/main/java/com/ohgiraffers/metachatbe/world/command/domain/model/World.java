package com.ohgiraffers.metachatbe.world.command.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("WORLD")
public class World {

    @Id
    @Column("ID")
    private Long id;

    @Column("ROOM_NAME")
    private String roomName;

    @Column("ROOM_PASSWORD")
    private String roomPassword;

    public World() {
    }

    public World(String roomName, String roomPassword) {
        this.roomName = roomName;
        this.roomPassword = roomPassword;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    @Override
    public String toString() {
        return "World{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", roomPassword='" + roomPassword + '\'' +
                '}';
    }
}
