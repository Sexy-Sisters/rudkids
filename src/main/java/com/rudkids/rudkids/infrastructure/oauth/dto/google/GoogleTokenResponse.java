package com.rudkids.rudkids.infrastructure.oauth.dto.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleTokenResponse {
    private String idToken;
    private String accessToken;

    private GoogleTokenResponse() {
    }

    public GoogleTokenResponse(String idToken, String accessToken) {
        this.idToken = idToken;
        this.accessToken = accessToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
