package com.ohgiraffers.metachatbe.chat.command.application.service;
import com.ohgiraffers.metachatbe.chat.command.application.dto.ChatDTO;
import com.ohgiraffers.metachatbe.chat.command.domain.model.Chat;
import com.ohgiraffers.metachatbe.chat.command.domain.repository.ChatRepository;
import com.ohgiraffers.metachatbe.chat.command.domain.service.ExternalUserService;
import com.ohgiraffers.metachatbe.chat.command.domain.service.ExternalZoneHistoryService;
import com.ohgiraffers.metachatbe.zonehistory.command.application.dto.ZoneHistoryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final ExternalUserService externalUserService;
    private final ExternalZoneHistoryService externalZoneHistoryService;

    public ChatService(ChatRepository chatRepository, ExternalUserService externalUserService, ExternalZoneHistoryService externalZoneHistoryService) {
        this.chatRepository = chatRepository;
        this.externalUserService = externalUserService;
        this.externalZoneHistoryService = externalZoneHistoryService;
    }


    public void saveChat(ChatDTO chatDTO) {
        Chat chat = chatDTO.toEntity();
        chatRepository.save(chat);
    }

    @Transactional(readOnly = true)
    public List<ChatDTO> getChatHistory(Long worldId, String userId) {
        List<ZoneHistoryDTO> zoneHistories = externalZoneHistoryService.getZoneHistories(worldId, userId);

        List<ChatDTO> allChats = new ArrayList<>();

        for (ZoneHistoryDTO dto : zoneHistories) {
            List<Chat> chats = chatRepository.findByWorldIdAndZoneNameAndChatTimeBetween(
                    worldId,
                    dto.getZoneName(),
                    dto.getEntryTime(),
                    dto.getExistTime());

            List<ChatDTO> chatDTOs = chats.stream().map(chat -> {
                String userName = externalUserService.getUserName(chat.getUserId());
                return new ChatDTO(chat.getZoneName(), userName, chat.getChatTime(), chat.getChatContent());
            }).toList();

            allChats.addAll(chatDTOs);
        }

        // Sort all chats by chat time
        allChats.sort(Comparator.comparing(ChatDTO::getChatTime));

        return allChats;
    }
}