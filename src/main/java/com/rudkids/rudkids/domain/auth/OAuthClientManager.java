package com.rudkids.rudkids.domain.auth;

import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;

public interface OAuthClientManager {
    boolean hasOAuthClient(String provider);
    AuthUser.OAuth getOAuthUser(String code, String redirectUri);
}
