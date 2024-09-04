package com.ohgiraffers.metachatbe.meeting.command.application.service;

import com.ohgiraffers.metachatbe.meeting.command.application.controller.dto.MeetingDTO;
import com.ohgiraffers.metachatbe.meeting.command.domain.model.Meeting;
import com.ohgiraffers.metachatbe.meeting.command.domain.repository.MeetingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeetingCreateService {
    private final MeetingRepository meetingRepository;

    public MeetingCreateService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Transactional
    public void createMeeting(MeetingDTO meetingDTO) {
//        Meeting meeting = meetingDTO.toEntity();
//        meetingRepository.save(meeting);
    }
}
