package com.rudkids.core.auth.infrastructure.dto;

import lombok.Builder;

import java.util.UUID;

public class AuthUser {

    public record Login(UUID id) {}

    @Builder
    public record OAuth(
        String email,
        String name,
        String picture
    ) {
    }
}
