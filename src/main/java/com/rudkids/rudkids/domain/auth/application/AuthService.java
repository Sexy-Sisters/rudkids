package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.domain.auth.dto.OAuthUser;
import com.rudkids.rudkids.domain.auth.dto.request.TokenRenewalRequest;
import com.rudkids.rudkids.domain.auth.dto.response.AccessAndRefreshTokenResponse;
import com.rudkids.rudkids.domain.auth.dto.response.AccessTokenResponse;

import java.util.UUID;

public interface AuthService {
    AccessAndRefreshTokenResponse generateAccessAndRefreshToken(OAuthUser oAuthUser);
    AccessTokenResponse generateRenewalAccessToken(TokenRenewalRequest tokenRenewalRequest);
    UUID extractUserId(String accessToken);
}
