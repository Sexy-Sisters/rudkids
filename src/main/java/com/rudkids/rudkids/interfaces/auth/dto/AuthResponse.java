package com.rudkids.rudkids.interfaces.auth.dto;

import lombok.Getter;

public class AuthResponse {

    @Getter
    public static class AccessAndRefreshToken {
        private final String accessToken;
        private final String refreshToken;

        public AccessAndRefreshToken(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    @Getter
    public static class AccessToken {
        private final String accessToken;

        public AccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @Getter
    public static class OAuthUri {
        private final String oAuthUri;

        public OAuthUri(String oAuthUri) {
            this.oAuthUri = oAuthUri;
        }
    }
}
