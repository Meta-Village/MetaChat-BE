package com.ohgiraffers.metachatbe.chat.command.domain.repository;

import com.ohgiraffers.metachatbe.chat.command.domain.model.Chat;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByWorldIdAndZoneNameAndChatTimeBetween(Long worldId, ZoneName zoneName, LocalDateTime start, LocalDateTime end);
}