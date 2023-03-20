package com.rudkids.rudkids.domain.auth.dto.response;

public class AccessAndRefreshTokenResponse {
    private String accessToken;
    private String refreshToken;

    private AccessAndRefreshTokenResponse() {
    }

    public AccessAndRefreshTokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
