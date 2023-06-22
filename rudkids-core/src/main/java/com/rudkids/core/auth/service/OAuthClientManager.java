package com.rudkids.core.auth.service;

import com.rudkids.core.auth.infrastructure.dto.AuthUser;

public interface OAuthClientManager {
    boolean isOAuthClient(String provider);
    AuthUser.OAuth getOAuthUser(String code, String redirectUri);
}
