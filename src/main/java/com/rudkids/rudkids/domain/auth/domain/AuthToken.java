package com.rudkids.rudkids.domain.auth.domain;

import com.rudkids.rudkids.domain.auth.exception.NoSuchTokenException;

public class AuthToken {
    private String accessToken;
    private String refreshToken;

    protected AuthToken() {
    }

    public AuthToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void validateHasSameRefreshToken(final String otherRefreshToken) {
        if (!refreshToken.equals(otherRefreshToken)) {
            throw new NoSuchTokenException();
        }
    }
}
