package com.ohgiraffers.metachatbe.chat.command.domain.model.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class World {

    @Column(name = "chat_world_id")
    private String chatWorldId;

    public World(String chatWorldId) {
        this.chatWorldId = chatWorldId;
    }

    @Override
    public String toString() {
        return "World{" +
                "chatWorldId='" + chatWorldId + '\'' +
                '}';
    }
}
