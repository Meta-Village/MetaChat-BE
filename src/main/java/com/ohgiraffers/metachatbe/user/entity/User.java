package com.ohgiraffers.metachatbe.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ohgiraffers.metachatbe.security.common.OhgiraffersRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TBL_USER")
@Schema(name= "사용자 데이터",description = "사용자 정보를 담고 있는 엔티티")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO")
    @Schema(description = "사용자의 고유 번호", example = "1")
    private int userNo;

    @Column(name = "USER_ID", nullable = false,unique = true)
    @Schema(description = "사용자의 ID", example = "user123")
    private String userId;

    @Column(name = "USER_PASS", nullable = false)
    @Schema(description = "사용자의 비밀번호", example = "password123")
    private String userPass;

    @Column(name = "USER_NAME")
    @Schema(description = "사용자의 이름", example = "홍길동")
    private String userName;

    @Column(name = "USER_EMAIL")
    @Schema(description = "사용자의 이메일 주소", example = "user@example.com")
    private String userEmail;

    @Column(name = "USER_FILE_NAME")
    @Schema(description = "사용자가 업로드한 파일 이름", example = "profile.jpg")
    private String userFileName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "USER_ROLE")
    @Schema(description = "사용자의 역할", example = "USER")
    private OhgiraffersRole role;

    @Column(name = "USER_STATE")
    @Schema(description = "사용자의 상태", example = "ACTIVE")
    private String state;
    @JsonIgnore
    public List<String> getRoleList() {
        if (this.role.getRole().length() > 0) {
            return Arrays.asList(this.role.getRole().split(","));
        }
        return new ArrayList<>();
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userFileName='" + userFileName + '\'' +
                ", role=" + role +
                ", state='" + state + '\'' +
                '}';
    }
}
