package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;

import java.util.UUID;

public interface AuthService {
    AuthResponse.AccessAndRefreshToken generateAccessAndRefreshToken(AuthCommand.OAuthUser oAuthUser);
    AuthResponse.AccessToken generateRenewalAccessToken(AuthCommand.RenewalToken tokenRenewalRequest);
    UUID extractUserId(String accessToken);
}
