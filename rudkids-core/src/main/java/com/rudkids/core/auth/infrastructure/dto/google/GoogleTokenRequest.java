package com.rudkids.core.auth.infrastructure.dto.google;

import lombok.Builder;

@Builder
public record GoogleTokenRequest(
    String clientId,
    String clientSecret,
    String code,
    String grantType,
    String redirectUri
) {
    public GoogleTokenRequest {
        grantType = "authorization_code";
    }
}