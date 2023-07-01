package com.rudkids.core.auth.infrastructure.dto.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@Getter
public class GoogleTokenResponse {
    private String idToken;

    public GoogleTokenResponse(String idToken) {
        this.idToken = idToken;
    }
}
