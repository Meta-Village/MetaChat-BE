package com.ohgiraffers.metachatbe.chat.command.application.controller;

import com.ohgiraffers.metachatbe.chat.command.application.dto.ChatDTO;
import com.ohgiraffers.metachatbe.chat.command.application.service.ChatService;
import com.ohgiraffers.metachatbe.util.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "채팅 관리", description = "채팅 기록 저장 및 조회를 담당하는 API입니다.")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @Operation(summary = "채팅 저장", description = "새로운 채팅 메시지를 저장하는 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅이 성공적으로 저장되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @PostMapping
    public ResponseEntity<ResponseMessage> saveChat(@RequestBody ChatDTO chatDTO) {
        try {
            chatService.saveChat(chatDTO);
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    @Operation(summary = "채팅 기록 조회", description = "특정 월드와 사용자 ID를 기반으로 채팅 기록을 조회하는 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅 기록이 성공적으로 전달되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류로 인해 채팅 기록을 가져오지 못했습니다.")
    })
    @GetMapping("/{worldId}")
    public ResponseEntity<ResponseMessage> getChatHistory(@PathVariable Long worldId, @RequestParam String userId) {
        try {
            List<ChatDTO> chatHistory = chatService.getChatHistory(worldId, userId);
            return ResponseEntity.ok(new ResponseMessage("챗 리스트 전달 성공", chatHistory));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("챗 리스트 전달 실패"));
        }
    }
}
