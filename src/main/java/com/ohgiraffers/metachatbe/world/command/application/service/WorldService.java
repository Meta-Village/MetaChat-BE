package com.ohgiraffers.metachatbe.world.command.application.service;

import com.ohgiraffers.metachatbe.world.command.application.dto.WorldRequestDTO;
import com.ohgiraffers.metachatbe.world.command.application.dto.WorldResponseDTO;
import com.ohgiraffers.metachatbe.world.command.domain.model.World;
import com.ohgiraffers.metachatbe.world.command.domain.repository.WorldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorldService {

    private final WorldRepository worldRepository;

    public WorldService(WorldRepository worldRepository) {
        this.worldRepository = worldRepository;
    }

    public WorldResponseDTO createWorld(WorldRequestDTO worldDTO) {
        World world = convertToEntity(worldDTO);
        World savedWorld = worldRepository.save(world);
        return convertToResponseDTO(savedWorld);
    }

    public Optional<WorldResponseDTO> getWorldById(Long id) {
        return worldRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    public List<WorldResponseDTO> getAllWorlds() {
        return worldRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public boolean updateWorld(Long id, WorldRequestDTO worldDTO) {
        Optional<World> existingWorldOpt = worldRepository.findById(id);
        if (existingWorldOpt.isPresent()) {
            World existingWorld = existingWorldOpt.get();
            existingWorld.setWorldName(worldDTO.getWorldName());
            existingWorld.setWorldPassword(worldDTO.getWorldPassword());
            worldRepository.save(existingWorld);
            return true;
        }
        return false;
    }

    public boolean deleteWorld(Long id) {
        if (worldRepository.existsById(id)) {
            worldRepository.deleteById(id);
            return true;
        }
        return false;
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
