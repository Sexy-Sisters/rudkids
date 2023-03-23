package com.rudkids.rudkids.domain.auth.dto.request;

public class TokenRenewalRequest {
    private String refreshToken;

    private TokenRenewalRequest() {
    }

    public TokenRenewalRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
