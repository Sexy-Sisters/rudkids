package com.rudkids.rudkids.domain.auth.application;

public class AuthCommand {

    public static class RenewalToken {
        private String refreshToken;

        private RenewalToken() {
        }

        public RenewalToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }
    }

    public static class OAuthUser {
        private String email;
        private String name;

        private OAuthUser() {
        }

        public OAuthUser(String email, String name) {
            this.email = email;
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }
    }
}
