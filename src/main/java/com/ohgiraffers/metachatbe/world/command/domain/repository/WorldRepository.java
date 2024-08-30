package com.ohgiraffers.metachatbe.world.command.domain.repository;


import com.ohgiraffers.metachatbe.world.command.domain.model.World;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface WorldRepository extends R2dbcRepository<World, Long> {
}
