package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthResponse;

public interface OAuthUri {

    AuthResponse.Uri generate(String provider, String redirectUri);
}