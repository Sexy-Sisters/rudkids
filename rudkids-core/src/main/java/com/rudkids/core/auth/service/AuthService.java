package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthRequest;
import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;

import java.util.UUID;

public interface AuthService {
    AuthResponse.AccessAndRefreshToken generateAccessAndRefreshToken(AuthUser.OAuth oAuthUser);
    AuthResponse.AccessToken generateRenewalAccessToken(AuthRequest.RenewalToken request);
    void registerInformation(UUID userId, AuthRequest.Register request);
    UUID extractUserId(String accessToken);
    void validateAdminAuthority(UUID userId);
    void validatePhoneNumber(UUID userId);
}