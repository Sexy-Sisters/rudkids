package com.rudkids.rudkids.domain.auth;

import com.rudkids.rudkids.domain.auth.domain.AuthToken;

import java.util.UUID;

public interface TokenCreator {
    AuthToken createAuthToken(UUID userId);
    AuthToken renewAuthToken(String refreshToken);
    UUID extractPayload(String accessToken);
}
