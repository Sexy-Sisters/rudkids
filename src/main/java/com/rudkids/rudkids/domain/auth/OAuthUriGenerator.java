package com.rudkids.rudkids.domain.auth;

import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;

public interface OAuthUriGenerator {
    boolean isOAuthUri(String provider);
    AuthResponse.OAuthUri generate(String redirectUri);
}
