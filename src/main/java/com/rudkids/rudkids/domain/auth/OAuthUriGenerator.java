package com.rudkids.rudkids.domain.auth;

import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;

public interface OAuthUriGenerator {
    boolean hasOAuthUri(String provider);
    AuthResponse.OAuthUri generate(String redirectUri);
}
