package com.ohgiraffers.metachatbe.user.repository;

import com.ohgiraffers.metachatbe.user.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserCommandRepository extends R2dbcRepository<User, Long> {

    Mono<Object> findByEmail(String userEmail);
}
