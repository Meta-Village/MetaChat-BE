package com.ohgiraffers.metachatbe.world.command.domain.repository;


import com.ohgiraffers.metachatbe.world.command.domain.model.World;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldRepository extends JpaRepository<World, Long> {
}
