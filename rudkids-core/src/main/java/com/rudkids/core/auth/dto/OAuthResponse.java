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
    public static class GoogleToken {
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
    public static class KakaoToken {
        private String idToken;
        private String refreshToken;
    }

    public record KakaoUserInfo(
        String email,
        String nickname,
        String picture
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class RenewaToken {
        private String accessToken;
        private String refreshToken;
    }
}
