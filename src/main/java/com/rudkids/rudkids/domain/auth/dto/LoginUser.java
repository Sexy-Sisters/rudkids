package com.rudkids.rudkids.domain.auth.dto;

import java.util.UUID;

public class LoginUser {
    private UUID id;

    private LoginUser() {
    }

    public LoginUser(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
