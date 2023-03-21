package com.rudkids.rudkids.infrastructure.oauth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleTokenResponse {
    private String idToken;

    private GoogleTokenResponse() {
    }

    public GoogleTokenResponse(String idToken) {
        this.idToken = idToken;
    }
}
