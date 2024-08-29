package com.ohgiraffers.metachatbe.user.service;

import com.ohgiraffers.metachatbe.user.repository.UserRepository;
import com.ohgiraffers.metachatbe.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(String id){
        Optional<User> user = userRepository.findByUserId(id);


        return user;
    }
}
