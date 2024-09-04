package com.ohgiraffers.metachatbe.chat.command.infrastructure.repository;

import com.ohgiraffers.metachatbe.chat.command.domain.service.ExternalUserService;
import com.ohgiraffers.metachatbe.user.entity.User;
import com.ohgiraffers.metachatbe.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaExternalUserService implements ExternalUserService {

    private final UserService userService;

    public JpaExternalUserService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String getUserName(String userId) {
        Optional<User> user = userService.findUser(userId);
        return user.map(User::getUserName).orElse("Unknown User");
    }
}