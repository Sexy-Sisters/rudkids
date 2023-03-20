package com.rudkids.rudkids.infrastructure.oauth.dto;

import lombok.Getter;

@Getter
public class UserInfo {
    private String email;
    private String name;

    private UserInfo() {
    }

    public UserInfo(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
