package com.rudkids.rudkids.domain.auth.service;

import com.rudkids.rudkids.domain.auth.AuthCommand;
import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;

import java.util.UUID;

public interface AuthService {
    AuthResponse.AccessAndRefreshToken generateAccessAndRefreshToken(AuthCommand.OAuthUser oAuthUser);
    AuthResponse.AccessToken generateRenewalAccessToken(AuthCommand.RenewalAccessToken tokenRenewalRequest);
    UUID extractUserId(String accessToken);
}