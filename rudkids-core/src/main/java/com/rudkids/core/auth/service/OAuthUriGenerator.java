package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthResponse;

public interface OAuthUriGenerator {
    AuthResponse.Uri generate(String redirectUri);
}
