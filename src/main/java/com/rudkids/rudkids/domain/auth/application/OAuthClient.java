package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;

public interface OAuthClient {
    AuthUser.OAuth getOAuthUser(String code, String redirectUri);
}
