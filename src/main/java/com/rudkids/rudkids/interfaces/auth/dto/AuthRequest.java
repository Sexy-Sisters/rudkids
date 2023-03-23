package com.rudkids.rudkids.interfaces.auth.dto;

import lombok.Getter;

public class AuthRequest {

    @Getter
    public static class RenewalToken {
        private String refreshToken;

        private RenewalToken() {
        }

        public RenewalToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }

    @Getter
    public static class Token {
        private String authorizationCode;
        private String redirectUri;

        private Token() {
        }

        public Token(String authorizationCode, String redirectUri) {
            this.authorizationCode = authorizationCode;
            this.redirectUri = redirectUri;
        }
    }
}
