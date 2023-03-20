package com.rudkids.rudkids.domain.auth.dto;

import lombok.Getter;

@Getter
public class OAuthUser {
    private final String email;
    private final String name;
    private final String refreshToken;

    public OAuthUser(String email, String name, String refreshToken) {
        this.email = email;
        this.name = name;
        this.refreshToken = refreshToken;
    }
}
