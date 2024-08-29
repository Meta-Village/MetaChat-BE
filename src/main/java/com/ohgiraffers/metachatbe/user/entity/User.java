package com.ohgiraffers.metachatbe.user.entity;

import com.ohgiraffers.metachatbe.security.auth.model.dto.Authority;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;


@Table("TBL_USER") // 테이블 매핑
public class User {

    @Id
    @Column("USER_ID")
    private Long id;

    @Column("USER_PASS")
    private String password;

    @Column("USER_NAME")
    private String userName;

    @Column("USER_EMAIL")
    private String email;

    @Column("USER_ROLE")
    private Authority role;

    @Column("USER_STATE")
    private String state;



    // 기본 생성자
    public User() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Authority getRole() {
        return role;
    }

    public void setRole(Authority role) {
        this.role = role;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", state='" + state + '\'' +
                '}';
    }
}
