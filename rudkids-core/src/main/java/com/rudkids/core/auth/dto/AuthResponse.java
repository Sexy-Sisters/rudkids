package com.rudkids.core.auth.dto;

public class AuthResponse {

    public record Uri(String oAuthUri) {}

    public record AccessAndRefreshToken(String accessToken, String refreshToken) {}

    public record Login(
        String accessToken,
        String refreshToken,
        boolean hasPhoneNumber
    ) {
        public Login(AccessAndRefreshToken token, boolean hasPhoneNumber) {
            this(token.accessToken, token.refreshToken, hasPhoneNumber);
        }
    }

    public record AccessToken(String accessToken) {}
}
