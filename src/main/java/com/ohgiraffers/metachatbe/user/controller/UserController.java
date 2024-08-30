package com.ohgiraffers.metachatbe.user.controller;

import com.ohgiraffers.metachatbe.user.repository.UserCommandRepository;
import com.ohgiraffers.metachatbe.user.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    private UserCommandRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Hidden
    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> signup(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setState("Y");

        return userRepository.save(user)
                .map(savedUser -> ResponseEntity.ok("회원가입 성공"))
                .defaultIfEmpty(ResponseEntity.status(500).body("회원가입 실패"));
    }
}
