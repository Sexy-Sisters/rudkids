package com.rudkids.rudkids.infrastructure.oauth.dto.google;

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