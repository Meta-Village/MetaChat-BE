package com.ohgiraffers.metachatbe.meeting.command.application.controller;

import com.ohgiraffers.metachatbe.meeting.command.application.dto.MeetingDTO;
import com.ohgiraffers.metachatbe.meeting.command.application.service.MeetingCreateService;
import com.ohgiraffers.metachatbe.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meeting")
public class MeetingController {
    private final MeetingCreateService meetingCreateService;

    public MeetingController(MeetingCreateService meetingCreateService) {
        this.meetingCreateService = meetingCreateService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createMeeting(@RequestBody MeetingDTO meetingDTO) {
        try{
            meetingCreateService.createMeeting(meetingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }
}
