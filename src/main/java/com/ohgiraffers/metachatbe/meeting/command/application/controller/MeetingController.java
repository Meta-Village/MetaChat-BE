package com.ohgiraffers.metachatbe.meeting.command.application.controller;

import com.ohgiraffers.metachatbe.meeting.command.application.dto.MeetingDTO;
import com.ohgiraffers.metachatbe.meeting.command.application.service.MeetingService;
import com.ohgiraffers.metachatbe.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meeting")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createMeeting(@RequestBody MeetingDTO meetingDTO) {
        try {
            meetingService.createMeeting(meetingDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseMessage("Meeting created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    @PutMapping("/{meetingId}")
    public ResponseEntity<ResponseMessage> updateMeeting(@RequestBody MeetingDTO meetingDTO) {
        try {
            meetingService.updateMeeting(meetingDTO);
            return ResponseEntity.ok(new ResponseMessage("Meeting updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/{meetingId}")
    public ResponseEntity<ResponseMessage> deleteMeeting(@PathVariable Long meetingId) {
        try {
            meetingService.deleteMeeting(meetingId);
            return ResponseEntity.ok(new ResponseMessage("Meeting deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    @PatchMapping("/{meetingId}/end")
    public ResponseEntity<ResponseMessage> endMeeting(@PathVariable Long meetingId) {
        try {
            meetingService.endMeeting(meetingId);
            return ResponseEntity.ok(new ResponseMessage("Meeting ended successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }
}