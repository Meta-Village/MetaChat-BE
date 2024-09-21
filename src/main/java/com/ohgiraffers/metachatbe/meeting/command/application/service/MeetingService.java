package com.ohgiraffers.metachatbe.meeting.command.application.service;

import com.ohgiraffers.metachatbe.meeting.command.application.dto.MeetingDTO;
import com.ohgiraffers.metachatbe.meeting.command.domain.model.Meeting;
import com.ohgiraffers.metachatbe.meeting.command.domain.repository.MeetingRepository;
import com.ohgiraffers.metachatbe.util.enumtype.ZoneName;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }


    public long createMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = meetingDTO.toEntity();
        meetingRepository.save(meeting);
        return meeting.getMeetId();
    }

    @Transactional
    public void updateMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = meetingRepository.findById(meetingDTO.getMeetingId())
                .orElseThrow(() -> new RuntimeException("Meeting not found"));

        meeting.update(meetingDTO.getMeetStartTime(), meetingDTO.getMeetEndTime(),
                meetingDTO.getZoneName(), meetingDTO.getWorldId());

        meetingRepository.save(meeting);
    }


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

    @Transactional
    public List<MeetingDTO> findMeetingByWorldIdAndZoneName(long worldId, ZoneName zoneName) {
        List<Meeting> meetings = meetingRepository.findByWorldIdAndZoneName(worldId, zoneName);
//        System.out.println(meetings);
        if (meetings.isEmpty()) {return null;}
        return meetings
                .stream()
                .map(meeting-> new MeetingDTO(
                        meeting.getMeetId(),
                        meeting.getMeetStartTime(),
                        meeting.getMeetEndTime(),
                        meeting.getZoneName(),
                        meeting.getWorldId()
                ))
                .toList();
    }
}
