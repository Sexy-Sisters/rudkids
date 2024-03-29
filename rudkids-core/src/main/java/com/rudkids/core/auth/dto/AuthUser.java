package com.rudkids.core.auth.dto;

import lombok.Builder;

import java.util.UUID;

public class AuthUser {

    public record Login(UUID id) {}

    @Builder
    public record OAuth(
        String email,
        String name,
        String picture,
        String refreshToken
    ) {
    }

    public record Renewal(String accessToken, String refreshToken) {}
}
