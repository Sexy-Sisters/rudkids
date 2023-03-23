package com.rudkids.rudkids.interfaces.auth.dto;

import lombok.Getter;

import java.util.UUID;

public class AuthUser {

    @Getter
    public static class Login {
        private UUID id;

        private Login() {
        }

        public Login(UUID id) {
            this.id = id;
        }
    }

    @Getter
    public static class OAuth {
        private String email;
        private String name;

        private OAuth() {
        }

        public OAuth(String email, String name) {
            this.email = email;
            this.name = name;
        }
    }
}
