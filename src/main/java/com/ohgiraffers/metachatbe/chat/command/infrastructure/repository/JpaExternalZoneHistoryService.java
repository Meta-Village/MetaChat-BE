package com.ohgiraffers.metachatbe.chat.command.infrastructure.repository;

import com.ohgiraffers.metachatbe.chat.command.domain.service.ExternalZoneHistoryService;
import com.ohgiraffers.metachatbe.zonehistory.command.application.dto.ZoneHistoryDTO;
import com.ohgiraffers.metachatbe.zonehistory.command.application.service.ZoneHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaExternalZoneHistoryService implements ExternalZoneHistoryService {
    private final ZoneHistoryService zoneHistoryService;

    public JpaExternalZoneHistoryService(ZoneHistoryService zoneHistoryService) {
        this.zoneHistoryService = zoneHistoryService;
    }

    @Override
    public List<ZoneHistoryDTO> getZoneHistories(Long worldId, String userId) {
        List<ZoneHistoryDTO> zoneHistories = zoneHistoryService.getZoneHistoriesByWorldIdAndUserId(worldId,userId);
        return zoneHistories; // Replace with actual implementation
    }
}