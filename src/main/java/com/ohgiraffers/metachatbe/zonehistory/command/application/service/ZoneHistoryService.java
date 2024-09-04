package com.ohgiraffers.metachatbe.zonehistory.command.application.service;

import com.ohgiraffers.metachatbe.zonehistory.command.application.dto.ZoneHistoryDTO;
import com.ohgiraffers.metachatbe.zonehistory.command.domain.model.ZoneHistory;
import com.ohgiraffers.metachatbe.zonehistory.command.domain.repository.ZoneHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoneHistoryService {
    private final ZoneHistoryRepository zoneHistoryRepository;

    public ZoneHistoryService(ZoneHistoryRepository zoneHistoryRepository) {
        this.zoneHistoryRepository = zoneHistoryRepository;
    }

    @Transactional
    public void insertZoneHistory(ZoneHistoryDTO zoneHistoryDTO) {
        ZoneHistory zoneHistory = zoneHistoryDTO.toEntity();
        zoneHistoryRepository.save(zoneHistory);
    }

    @Transactional(readOnly = true)
    public List<ZoneHistoryDTO> getZoneHistoriesByWorldIdAndUserId(Long worldId, String userId) {
        List<ZoneHistory> zoneHistories = zoneHistoryRepository.findByWorldIdAndUserId(worldId, userId);
        return zoneHistories.stream()
                .map(ZoneHistoryDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
