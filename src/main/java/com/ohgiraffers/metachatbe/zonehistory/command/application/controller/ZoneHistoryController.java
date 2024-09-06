package com.ohgiraffers.metachatbe.zonehistory.command.application.controller;

import com.ohgiraffers.metachatbe.util.ResponseMessage;
import com.ohgiraffers.metachatbe.zonehistory.command.application.dto.ZoneHistoryDTO;
import com.ohgiraffers.metachatbe.zonehistory.command.application.service.ZoneHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zoneHistory")
@Tag(name = "구역 기록 관리", description = "구역 기록 데이터를 관리하는 API입니다.")
public class ZoneHistoryController {

    private final ZoneHistoryService zoneHistoryService;

    public ZoneHistoryController(ZoneHistoryService zoneHistoryService) {
        this.zoneHistoryService = zoneHistoryService;
    }

    @Operation(summary = "구역 기록 삽입", description = "구역 기록 데이터를 삽입하는 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "구역 기록 데이터가 성공적으로 삽입되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 데이터가 유효하지 않습니다.")
    })
    @PostMapping
    public ResponseEntity<ResponseMessage> insertZoneHistory(@RequestBody ZoneHistoryDTO zoneHistoryDTO) {
        try {
            zoneHistoryService.insertZoneHistory(zoneHistoryDTO);
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }
}
