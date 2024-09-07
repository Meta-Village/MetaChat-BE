package com.ohgiraffers.metachatbe.security.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OhgiraffersRole {

    USER("USER"),
    ADMIN("ADMIN"),
    ALL("USER,ADMIN");

    private String role;

    OhgiraffersRole(String role) {
        this.role = role;
    }
    @JsonValue
    public String getRole() {
        return role;
    }
}
