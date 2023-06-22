package com.rudkids.core.auth.dto;

public class AuthRequest {

    public record Token(String authorizationCode, String redirectUri) {}

    public record RenewalToken(String refreshToken) {}
}
