package com.rudkids.core.auth.service;

import com.rudkids.core.auth.domain.AuthToken;

import java.util.UUID;

public interface TokenCreator {
    AuthToken createAuthToken(UUID userId);
    AuthToken renewAuthToken(String refreshToken);
    UUID extractPayload(String accessToken);
}
