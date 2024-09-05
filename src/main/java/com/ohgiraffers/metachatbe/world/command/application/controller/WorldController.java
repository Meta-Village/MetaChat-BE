package com.ohgiraffers.metachatbe.world.command.application.controller;

import com.ohgiraffers.metachatbe.world.command.application.dto.WorldRequestDTO;
import com.ohgiraffers.metachatbe.world.command.application.dto.WorldResponseDTO;
import com.ohgiraffers.metachatbe.world.command.application.service.WorldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/worlds")
public class WorldController {

    private final WorldService worldService;

    public WorldController(WorldService worldService) {
        this.worldService = worldService;
    }

    @Operation(summary = "Create a new World", description = "Creates a new World entity and returns the created entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "World created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<WorldResponseDTO> createWorld(@RequestBody WorldRequestDTO world) {
        WorldResponseDTO createdWorld = worldService.createWorld(world);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorld);
    }

    @Operation(summary = "Get World by ID", description = "Retrieves a World entity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "World retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "World not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<WorldResponseDTO> getWorldById(@PathVariable Long id) {
        Optional<WorldResponseDTO> world = worldService.getWorldById(id);
        return world.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Get all Worlds", description = "Retrieves all World entities.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Worlds retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<WorldResponseDTO>> getAllWorlds() {
        List<WorldResponseDTO> worlds = worldService.getAllWorlds();
        return ResponseEntity.ok(worlds);
    }

    @Operation(summary = "Update World", description = "Updates an existing World entity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "World updated successfully"),
            @ApiResponse(responseCode = "404", description = "World not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorld(@PathVariable Long id, @RequestBody WorldRequestDTO world) {
        boolean isUpdated = worldService.updateWorld(id, world);
        if (isUpdated) {
            return ResponseEntity.ok("변경되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("World not found");
        }
    }

    @Operation(summary = "Delete World", description = "Deletes a World entity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "World deleted successfully"),
            @ApiResponse(responseCode = "404", description = "World not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorld(@PathVariable Long id) {
        boolean isDeleted = worldService.deleteWorld(id);
        if (isDeleted) {
            return ResponseEntity.ok("제거되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("World not found");
        }
    }
}
