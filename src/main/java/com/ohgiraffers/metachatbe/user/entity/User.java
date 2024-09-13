package com.ohgiraffers.metachatbe.user.entity;

import com.ohgiraffers.metachatbe.security.common.OhgiraffersRole;
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
public class User {

    @Id
    @Column(name = "USER_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;

    @Column(name = "USER_ID", nullable = false,unique = true)
    private String userId;

    @Column(name = "USER_PASS",nullable = false)
    private String userPass;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_FILE_NAME")
    private String userFileName = ""; // Initialize with empty string

    @Enumerated(value = EnumType.STRING)
    @Column(name = "USER_ROLE")
    private OhgiraffersRole role;

    @Column(name = "USER_STATE")
    private String state;

    public List<String> getRoleList(){
        if(this.role.getRole().length() > 0){
            return Arrays.asList(this.role.getRole().split(","));
        }
        return new ArrayList<>();
    }


    public User() {
        this.role = OhgiraffersRole.USER; // 기본 역할 설정
    }


    public String getUserFileName() {
        return userFileName != null ? userFileName : "";
    }

    public void setUserFileName(String userFileName) {
        this.userFileName = userFileName != null ? userFileName : "";
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
