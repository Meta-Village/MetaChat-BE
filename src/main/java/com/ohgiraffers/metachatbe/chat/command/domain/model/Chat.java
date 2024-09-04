package com.ohgiraffers.metachatbe.chat.command.domain.model;

import com.ohgiraffers.metachatbe.chat.command.domain.model.embedded.User;
import com.ohgiraffers.metachatbe.chat.command.domain.model.embedded.World;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "chat_time")
    private LocalDateTime chatTime;

    @Column(name = "zone_name")
    private ZoneName zoneName;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "world_id")
    private long worldId;

    public Chat(LocalDateTime chatTime, ZoneName zoneName, long userId, long worldId) {
        this.chatTime = chatTime;
        this.zoneName = zoneName;
        this.userId = userId;
        this.worldId = worldId;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", chatTime=" + chatTime +
                ", zoneName=" + zoneName +
                ", userId=" + userId +
                ", worldId=" + worldId +
                '}';
    }
}
