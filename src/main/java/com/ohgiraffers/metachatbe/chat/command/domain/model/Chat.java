package com.ohgiraffers.metachatbe.chat.command.domain.model;

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

    @Column(name = "chat_content")
    private String chatContent;

    @Column(name = "zone_name")
    private ZoneName zoneName;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "world_id")
    private long worldId;

    @Column(name = "meeting_id")
    private long meetingId;

    public Chat(LocalDateTime chatTime, String chatContent, ZoneName zoneName, String userId, long worldId, long meetingId) {
        this.chatTime = chatTime;
        this.chatContent = chatContent;
        this.zoneName = zoneName;
        this.userId = userId;
        this.worldId = worldId;
        this.meetingId = meetingId;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", chatTime=" + chatTime +
                ", chatContent='" + chatContent + '\'' +
                ", zoneName=" + zoneName +
                ", userId=" + userId +
                ", worldId=" + worldId +
                ", meetingId=" + meetingId +
                '}';
    }
}
