package com.rudkids.rudkids.interfaces.auth.dto;

import lombok.Getter;

public class AuthResponse {

    @Getter
    public static class AccessAndRefreshToken {
        private String accessToken;
        private String refreshToken;

        private AccessAndRefreshToken() {
        }

        public AccessAndRefreshToken(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    @Getter
    public static class AccessToken {
        private String accessToken;

        private AccessToken() {
        }

        public AccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    @Getter
    public static class OAuthUri {
        private String oAuthUri;

        private OAuthUri() {
        }

        public OAuthUri(String oAuthUri) {
            this.oAuthUri = oAuthUri;
        }
    }
}
