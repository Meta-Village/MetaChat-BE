package com.ohgiraffers.metachatbe.zonehistory.command.application.controller;

import com.ohgiraffers.metachatbe.util.ResponseMessage;
import com.ohgiraffers.metachatbe.zonehistory.command.application.dto.ZoneHistoryDTO;
import com.ohgiraffers.metachatbe.zonehistory.command.application.service.ZoneHistoryInsertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zoneHistory")
public class ZoneHistoryController {

    private final ZoneHistoryInsertService zoneHistoryInsertService;

    public ZoneHistoryController(ZoneHistoryInsertService zoneHistoryInsertService) {
        this.zoneHistoryInsertService = zoneHistoryInsertService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> insertZoneHistory(@RequestBody ZoneHistoryDTO zoneHistoryDTO) {
        try {
            zoneHistoryInsertService.insertZoneHistory(zoneHistoryDTO);
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value()));
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage( e.getMessage()));
        }
    }
}