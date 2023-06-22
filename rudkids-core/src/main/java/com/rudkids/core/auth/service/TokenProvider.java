package com.rudkids.core.auth.service;

public interface TokenProvider {
    String createAccessToken(String payload);
    String createRefreshToken(String payload);
    void validateToken(String token);
    String getPayload(String token);
}
