package com.ohgiraffers.metachatbe.world.command.application.service;

import com.ohgiraffers.metachatbe.world.command.application.dto.WorldRequestDTO;
import com.ohgiraffers.metachatbe.world.command.application.dto.WorldResponseDTO;
import com.ohgiraffers.metachatbe.world.command.domain.model.World;
import com.ohgiraffers.metachatbe.world.command.domain.repository.WorldRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WorldService {

    private final WorldRepository worldRepository;

    public WorldService(WorldRepository worldRepository) {
        this.worldRepository = worldRepository;
    }

    public Mono<WorldResponseDTO> createWorld(WorldRequestDTO worldDTO) {
        World world = convertToEntity(worldDTO);
        return worldRepository.save(world)
                .map(this::convertToResponseDTO);
    }

    public Mono<WorldResponseDTO> getWorldById(Long id) {
        return worldRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    public Flux<WorldResponseDTO> getAllWorlds() {
        return worldRepository.findAll()
                .map(this::convertToResponseDTO);
    }

    public Mono<WorldResponseDTO> updateWorld(Long id, WorldRequestDTO worldDTO) {
        return worldRepository.findById(id)
                .flatMap(existingWorld -> {
                    existingWorld.setWorldName(worldDTO.getWorldName());
                    existingWorld.setWorldPassword(worldDTO.getWorldPassword());
                    return worldRepository.save(existingWorld);
                })
                .map(this::convertToResponseDTO);
    }

    public Mono<Void> deleteWorld(Long id) {
        return worldRepository.deleteById(id);
    }

    // DTO를 엔티티로 변환하는 메서드
    private World convertToEntity(WorldRequestDTO worldDTO) {
        return new World(worldDTO.getWorldName(), worldDTO.getWorldPassword());
    }

    // 엔티티를 DTO로 변환하는 메서드
    private WorldResponseDTO convertToResponseDTO(World world) {
        return new WorldResponseDTO(world.getId(), world.getWorldName());
    }
}
