package com.rudkids.core.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OAuthResponse {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class GoogleToken {
        private String idToken;
    }

    public record GoogleUserInfo(
        String email,
        String name,
        String picture
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public class KakaoToken {
        private String idToken;
    }

    public record KakaoUserInfo(
        String email,
        String nickname,
        String picture
    ) {}
}
