package com.ohgiraffers.metachatbe.user.service;

import com.ohgiraffers.metachatbe.user.repository.UserRepository;
import com.ohgiraffers.metachatbe.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(String id){
        return userRepository.findByUserId(id);
    }
}
