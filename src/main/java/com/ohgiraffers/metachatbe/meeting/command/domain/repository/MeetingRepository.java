package com.ohgiraffers.metachatbe.meeting.command.domain.repository;

import com.ohgiraffers.metachatbe.meeting.command.domain.model.Meeting;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByWorldIdAndZoneName(long worldId, ZoneName zoneName);
}
