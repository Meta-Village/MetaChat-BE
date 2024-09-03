package com.ohgiraffers.metachatbe.chat.command.domain.model.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class User {

    @Column(name = "chat_user_id")
    private String chatUserId;

    public User(String chatUserId) {
        this.chatUserId = chatUserId;
    }

    @Override
    public String toString() {
        return "User{" +
                "chatUserId='" + chatUserId + '\'' +
                '}';
    }
}
