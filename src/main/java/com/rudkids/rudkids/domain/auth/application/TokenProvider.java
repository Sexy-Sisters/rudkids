package com.rudkids.rudkids.domain.auth.application;

public interface TokenProvider {
    String createAccessToken(final String payload);
    String createRefreshToken(final String payload);
    void validateToken(String token);
    String getPayload(String token);
}
