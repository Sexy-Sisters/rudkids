package com.rudkids.rudkids.domain.auth;

import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;

public interface OAuthClient {
    AuthUser.OAuth getOAuthUser(String provider, String code, String redirectUri);
}
