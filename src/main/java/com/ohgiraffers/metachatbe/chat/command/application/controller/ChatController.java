package com.ohgiraffers.metachatbe.chat.command.application.controller;

import com.ohgiraffers.metachatbe.chat.command.application.dto.ChatDTO;
import com.ohgiraffers.metachatbe.chat.command.application.service.ChatService;
import com.ohgiraffers.metachatbe.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> saveChat(@RequestBody ChatDTO chatDTO) {
        try{
            chatService.saveChat(chatDTO);
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value()));
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage( e.getMessage()));
        }
    }

    @GetMapping("/{worldId}")
    public ResponseEntity<ResponseMessage> getChatHistory(@PathVariable Long worldId, @RequestParam String userId) {
        try {
            List<ChatDTO> chatHistory = chatService.getChatHistory(worldId, userId);
            return ResponseEntity.ok(new ResponseMessage("챗리스트 전달 성공", chatHistory));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("챗 리스트 전달 실패"));
        }
    }
}