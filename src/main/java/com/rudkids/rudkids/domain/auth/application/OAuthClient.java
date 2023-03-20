package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.domain.auth.dto.OAuthUser;
import com.rudkids.rudkids.domain.auth.dto.response.OAuthAccessTokenResponse;

public interface OAuthClient {
    OAuthUser getOAuthUser(String code, String redirectUri);

    OAuthAccessTokenResponse getAccessToken(String refreshToken);
}
