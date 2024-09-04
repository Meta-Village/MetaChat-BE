package com.ohgiraffers.metachatbe.zonehistory.command.application.service;

import com.ohgiraffers.metachatbe.zonehistory.command.application.dto.ZoneHistoryDTO;
import com.ohgiraffers.metachatbe.zonehistory.command.domain.model.ZoneHistory;
import com.ohgiraffers.metachatbe.zonehistory.command.domain.repository.ZoneHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZoneHistoryInsertService {
    private final ZoneHistoryRepository zoneHistoryRepository;

    public ZoneHistoryInsertService(ZoneHistoryRepository zoneHistoryRepository) {
        this.zoneHistoryRepository = zoneHistoryRepository;
    }

    @Transactional
    public void insertZoneHistory(ZoneHistoryDTO zoneHistoryDTO) {
        ZoneHistory zoneHistory = zoneHistoryDTO.toEntity();
        zoneHistoryRepository.save(zoneHistory);
    }
}
