package com.ohgiraffers.metachatbe.meeting.command.application.controller;

import com.ohgiraffers.metachatbe.meeting.command.application.dto.MeetingDTO;
import com.ohgiraffers.metachatbe.meeting.command.application.service.MeetingService;
import com.ohgiraffers.metachatbe.util.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meeting")
@Tag(name = "미팅 관리", description = "미팅 생성, 업데이트, 삭제 및 종료 API를 관리하는 컨트롤러")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Operation(summary = "미팅 생성", description = "새로운 미팅을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "미팅이 성공적으로 생성되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @PostMapping
    public ResponseEntity<ResponseMessage> createMeeting(@RequestBody MeetingDTO meetingDTO) {
        try {
            long meetingId = meetingService.createMeeting(meetingDTO);
            return ResponseEntity.ok(new ResponseMessage("미팅이 성공적으로 생성되었습니다.", meetingId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    @Operation(summary = "미팅 수정", description = "기존 미팅 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "미팅이 성공적으로 수정되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @PutMapping("/{meetingId}")
    public ResponseEntity<ResponseMessage> updateMeeting(@RequestBody MeetingDTO meetingDTO) {
        try {
            meetingService.updateMeeting(meetingDTO);
            return ResponseEntity.ok(new ResponseMessage("미팅이 성공적으로 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    @Operation(summary = "미팅 삭제", description = "기존 미팅을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "미팅이 성공적으로 삭제되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @DeleteMapping("/{meetingId}")
    public ResponseEntity<ResponseMessage> deleteMeeting(@PathVariable Long meetingId) {
        try {
            meetingService.deleteMeeting(meetingId);
            return ResponseEntity.ok(new ResponseMessage("미팅이 성공적으로 삭제되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    @Operation(summary = "미팅 종료", description = "미팅을 종료합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "미팅이 성공적으로 종료되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @PatchMapping("/{meetingId}/end")
    public ResponseEntity<ResponseMessage> endMeeting(@PathVariable Long meetingId) {
        try {
            meetingService.endMeeting(meetingId);
            return ResponseEntity.ok(new ResponseMessage("미팅이 성공적으로 종료되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }
}
