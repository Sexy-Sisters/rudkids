package com.rudkids.core.auth.service;

import com.rudkids.core.auth.infrastructure.dto.AuthUser;

public interface OAuthClient {
    AuthUser.OAuth getOAuthUser(String provider, String code, String redirectUri);
}
