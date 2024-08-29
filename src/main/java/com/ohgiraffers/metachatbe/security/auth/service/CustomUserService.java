package com.ohgiraffers.metachatbe.security.auth.service;

import com.ohgiraffers.metachatbe.security.auth.model.dto.CustomUserDetail;
import com.ohgiraffers.metachatbe.user.entity.User;
import com.ohgiraffers.metachatbe.user.repository.UserCommandRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomUserService implements ReactiveUserDetailsService {

    private final UserCommandRepository userRepository;

    public CustomUserService(UserCommandRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Mono<UserDetails> findByUsername(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found with email: " + userEmail)))
                .flatMap(user -> checkWithdrawalStatus((User)user)
                        .map(CustomUserDetail::new));
    }

    private Mono<User> checkWithdrawalStatus(User user) {
        if ("탈퇴".equals(user.getState())) {
            user.setState("활성");
            return userRepository.save(user);  // 비동기적으로 저장
        }
        return Mono.just(user);  // 이미 활성화된 경우 그대로 반환
    }
}
