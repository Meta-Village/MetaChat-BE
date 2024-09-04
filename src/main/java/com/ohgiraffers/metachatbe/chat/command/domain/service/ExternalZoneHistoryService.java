package com.ohgiraffers.metachatbe.chat.command.domain.service;

import com.ohgiraffers.metachatbe.zonehistory.command.application.dto.ZoneHistoryDTO;
import java.util.List;

public interface ExternalZoneHistoryService {
    List<ZoneHistoryDTO> getZoneHistories(Long worldId, String userId);
}