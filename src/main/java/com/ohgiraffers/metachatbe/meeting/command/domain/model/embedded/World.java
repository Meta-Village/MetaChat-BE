package com.ohgiraffers.metachatbe.meeting.command.domain.model.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class World {

    @Column(name = "world_id")
    private String worldId;

    public World(String worldId) {
        this.worldId = worldId;
    }

    @Override
    public String toString() {
        return "World{" +
                "worldId='" + worldId + '\'' +
                '}';
    }
}
