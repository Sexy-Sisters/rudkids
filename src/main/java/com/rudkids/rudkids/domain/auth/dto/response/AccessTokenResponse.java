package com.rudkids.rudkids.domain.auth.dto.response;

public class AccessTokenResponse {
    private String accessToken;

    private AccessTokenResponse() {
    }

    public AccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
