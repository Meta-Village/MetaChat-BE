package com.ohgiraffers.metachatbe.zonehistory.command.domain.repository;

import com.ohgiraffers.metachatbe.zonehistory.command.domain.model.ZoneHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneHistoryRepository extends JpaRepository<ZoneHistory, Long> {
}
