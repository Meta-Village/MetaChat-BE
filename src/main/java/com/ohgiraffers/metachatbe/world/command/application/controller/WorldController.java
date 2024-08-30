package com.ohgiraffers.metachatbe.world.command.application.controller;

import com.ohgiraffers.metachatbe.world.command.application.dto.WorldRequestDTO;
import com.ohgiraffers.metachatbe.world.command.application.dto.WorldResponseDTO;
import com.ohgiraffers.metachatbe.world.command.application.service.WorldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/worlds")
public class WorldController {

    private final WorldService worldService;

    public WorldController(WorldService worldService) {
        this.worldService = worldService;
    }

    @Operation(summary = "Create a new World", description = "Creates a new World entity and returns the created entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "World created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public Mono<WorldResponseDTO> createWorld(@RequestBody WorldRequestDTO world) {
        return worldService.createWorld(world);
    }

    @Operation(summary = "Get World by ID", description = "Retrieves a World entity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "World retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "World not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public Mono<WorldResponseDTO> getWorldById(@PathVariable Long id) {
        return worldService.getWorldById(id);
    }

    @Operation(summary = "Get all Worlds", description = "Retrieves all World entities.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Worlds retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Flux<WorldResponseDTO> getAllWorlds() {
        return worldService.getAllWorlds();
    }

    @Operation(summary = "Update World", description = "Updates an existing World entity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "World updated successfully"),
            @ApiResponse(responseCode = "404", description = "World not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public Mono<ResponseEntity<String>> updateWorld(@PathVariable Long id, @RequestBody WorldRequestDTO world) {
        return worldService.updateWorld(id, world)
                .then(Mono.just(ResponseEntity.ok("변경되었습니다.")));
    }

    @Operation(summary = "Delete World", description = "Deletes a World entity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "World deleted successfully"),
            @ApiResponse(responseCode = "404", description = "World not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deleteWorld(@PathVariable Long id) {
        return worldService.deleteWorld(id)
                .then(Mono.just(ResponseEntity.ok("제거되었습니다.")));
    }
}
