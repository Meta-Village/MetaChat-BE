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

    @Embedded
    private User user;

    @Embedded
    private World world;

    public Chat(LocalDateTime chatTime, ZoneName zoneName, User user, World world) {
        this.chatTime = chatTime;
        this.zoneName = zoneName;
        this.user = user;
        this.world = world;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", chatTime=" + chatTime +
                ", zoneName=" + zoneName +
                ", user=" + user +
                ", world=" + world +
                '}';
    }
}
