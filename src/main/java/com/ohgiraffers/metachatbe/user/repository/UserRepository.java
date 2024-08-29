package com.ohgiraffers.metachatbe.user.repository;

import com.ohgiraffers.metachatbe.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(String id);
}
