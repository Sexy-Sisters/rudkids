package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthResponse;

public interface OAuthUriGenerator {
    boolean isOAuthUri(String provider);
    AuthResponse.Uri generate(String redirectUri);
}
