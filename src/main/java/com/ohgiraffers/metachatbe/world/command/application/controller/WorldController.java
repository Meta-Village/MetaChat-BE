package com.ohgiraffers.metachatbe.world.command.application.controller;

import com.ohgiraffers.metachatbe.world.command.application.dto.WorldRequestDTO;
import com.ohgiraffers.metachatbe.world.command.application.dto.WorldResponseDTO;
import com.ohgiraffers.metachatbe.world.command.application.service.WorldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/worlds")
@Tag(name = "월드 관리", description = "월드 관련 API를 관리하는 컨트롤러")
public class WorldController {

    private final WorldService worldService;

    public WorldController(WorldService worldService) {
        this.worldService = worldService;
    }

    @Operation(summary = "새로운 월드 생성", description = "새로운 월드 엔티티를 생성하고 생성된 엔티티를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "월드가 성공적으로 생성되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력 데이터가 유효하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류가 발생했습니다.")
    })
    @PostMapping
    public ResponseEntity<WorldResponseDTO> createWorld(@RequestBody WorldRequestDTO world) {
        WorldResponseDTO createdWorld = worldService.createWorld(world);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorld);
    }

    @Operation(summary = "ID로 월드 조회", description = "월드 ID를 사용하여 월드 엔티티를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "월드가 성공적으로 조회되었습니다."),
            @ApiResponse(responseCode = "404", description = "월드를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류가 발생했습니다.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<WorldResponseDTO> getWorldById(@PathVariable Long id) {
        Optional<WorldResponseDTO> world = worldService.getWorldById(id);
        return world.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "모든 월드 조회", description = "모든 월드 엔티티를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "월드 목록이 성공적으로 조회되었습니다."),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류가 발생했습니다.")
    })
    @GetMapping
    public ResponseEntity<List<WorldResponseDTO>> getAllWorlds() {
        List<WorldResponseDTO> worlds = worldService.getAllWorlds();
        return ResponseEntity.ok(worlds);
    }

    @Operation(summary = "월드 업데이트", description = "ID를 사용하여 기존의 월드 엔티티를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "월드가 성공적으로 업데이트되었습니다."),
            @ApiResponse(responseCode = "404", description = "월드를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "400", description = "입력 데이터가 유효하지 않습니다."),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류가 발생했습니다.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorld(@PathVariable Long id, @RequestBody WorldRequestDTO world) {
        boolean isUpdated = worldService.updateWorld(id, world);
        if (isUpdated) {
            return ResponseEntity.ok("변경되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("월드를 찾을 수 없습니다.");
        }
    }

    @Operation(summary = "월드 삭제", description = "ID를 사용하여 월드 엔티티를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "월드가 성공적으로 삭제되었습니다."),
            @ApiResponse(responseCode = "404", description = "월드를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류가 발생했습니다.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorld(@PathVariable Long id) {
        boolean isDeleted = worldService.deleteWorld(id);
        if (isDeleted) {
            return ResponseEntity.ok("제거되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("월드를 찾을 수 없습니다.");
        }
    }
}
