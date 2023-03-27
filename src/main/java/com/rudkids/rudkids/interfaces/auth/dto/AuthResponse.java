package com.rudkids.rudkids.interfaces.auth.dto;

public class AuthResponse {

    public record AccessAndRefreshToken(String accessToken, String refreshToken) {
    }

    public record AccessToken(String accessToken) {
    }

    public record OAuthUri(String oAuthUri) {
    }
}
