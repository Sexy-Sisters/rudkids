package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthUser;

public interface OAuthClientManager {
    AuthUser.OAuth getOAuthUser(String code, String redirectUri);
}
