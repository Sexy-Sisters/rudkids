package com.rudkids.rudkids.interfaces.auth.dto;

import lombok.Builder;

public class AuthRequest {

    @Builder
    public record RenewalToken(String refreshToken) {
    }

    @Builder
    public record Token(String authorizationCode, String redirectUri) {
    }

    @Builder
    public record Validate(String token) {
    }
}
