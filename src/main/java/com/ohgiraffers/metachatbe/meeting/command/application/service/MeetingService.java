package com.ohgiraffers.metachatbe.meeting.command.application.service;

import com.ohgiraffers.metachatbe.meeting.command.application.dto.MeetingDTO;
import com.ohgiraffers.metachatbe.meeting.command.domain.model.Meeting;
import com.ohgiraffers.metachatbe.meeting.command.domain.repository.MeetingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Transactional
    public void createMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = meetingDTO.toEntity();
        meetingRepository.save(meeting);
    }

    @Transactional
    public void updateMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = meetingRepository.findById(meetingDTO.getMeetingId())
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        meeting.update(meetingDTO.getMeetStartTime(), meetingDTO.getMeetEndTime(),
                meetingDTO.getZoneName(), meetingDTO.getWorldId());

        meetingRepository.save(meeting);
    }

    @Transactional
    public void deleteMeeting(Long meetingId) {
        meetingRepository.deleteById(meetingId);
    }

    @Transactional
    public void endMeeting(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        meeting.end(LocalDateTime.now());

        meetingRepository.save(meeting);
    }
}
