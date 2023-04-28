package com.rudkids.rudkids.domain.auth;

import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;

public interface OAuthUri {

    AuthResponse.OAuthUri generate(String provider, String redirectUri);
}
