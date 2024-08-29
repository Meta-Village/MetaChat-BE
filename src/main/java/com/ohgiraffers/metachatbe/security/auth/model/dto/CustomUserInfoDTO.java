package com.ohgiraffers.metachatbe.security.auth.model.dto;


import com.ohgiraffers.metachatbe.user.entity.User;
import lombok.Data;

@Data
public class CustomUserInfoDTO {
    private Long userId;
    private String email;
    private Authority role;
    private String username;

    public CustomUserInfoDTO() {
    }

    public CustomUserInfoDTO(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.username= user.getUserName();
    }
}
