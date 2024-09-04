package com.ohgiraffers.metachatbe.chat.command.application.dto;

import com.ohgiraffers.metachatbe.chat.command.domain.model.Chat;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private Long worldId;
    private String userId;
    private Long meetingId;
    private String userName;
    private ZoneName zoneName;
    private LocalDateTime chatTime;
    private String chatContent;

    public ChatDTO(ZoneName zoneName, String userName, LocalDateTime chatTime, String chatContent) {
        this.zoneName = zoneName;
        this.userName = userName;
        this.chatTime = chatTime;
        this.chatContent = chatContent;
    }

    public Chat toEntity() {
        return new Chat(
                this.chatTime,
                this.chatContent,
                this.zoneName,
                this.userId,
                this.worldId,
                this.meetingId
        );
    }
}