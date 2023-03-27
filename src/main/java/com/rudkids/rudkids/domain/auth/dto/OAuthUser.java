package com.rudkids.rudkids.domain.auth.dto;

public class OAuthUser {
    private final String email;
    private final String name;

    public OAuthUser(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
