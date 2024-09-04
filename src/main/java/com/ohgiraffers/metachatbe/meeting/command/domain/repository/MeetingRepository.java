package com.ohgiraffers.metachatbe.meeting.command.domain.repository;

import com.ohgiraffers.metachatbe.meeting.command.domain.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
