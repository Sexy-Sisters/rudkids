package com.rudkids.rudkids.domain.auth.application;

public interface TokenProvider {
    String createAccessToken(String payload);
    String createRefreshToken(String payload);
    void validateToken(String token);
    String getPayload(String token);
}
