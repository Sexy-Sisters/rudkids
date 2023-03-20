package com.rudkids.rudkids.infrastructure.oauth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleTokenResponse {
    private String refreshToken;
    private String idToken;

    private GoogleTokenResponse() {
    }

    public GoogleTokenResponse(String refreshToken, String idToken) {
        this.refreshToken = refreshToken;
        this.idToken = idToken;
    }
}
