package com.ohgiraffers.metachatbe.chat.command.application.dto;

import com.ohgiraffers.metachatbe.chat.command.domain.model.Chat;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "채팅 데이터", description = "채팅 메시지와 관련된 데이터 전송 객체")
public class ChatDTO {

    @Schema( description = "채팅이 이루어지는 월드의 고유 ID", example = "1")
    private Long worldId;

    @Schema( description = "채팅을 작성한 사용자의 ID", example = "user123")
    private String userId;

    @Schema( description = "채팅이 관련된 미팅의 고유 ID", example = "101")
    private Long meetingId;

    @Schema( description = "채팅을 작성한 사용자의 이름", example = "홍길동")
    private String userName;

    @Schema( description = "채팅이 발생한 존의 이름", example = "MainZone")
    private ZoneName zoneName;

    @Schema( description = "채팅이 발생한 시간", example = "2023-09-06T14:30:00")
    private LocalDateTime chatTime;

    @Schema( description = "채팅의 실제 내용", example = "안녕하세요, 모두들!")
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
