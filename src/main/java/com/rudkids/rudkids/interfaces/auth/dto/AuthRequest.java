package com.rudkids.rudkids.interfaces.auth.dto;

public class AuthRequest {

        public record RenewalToken(String refreshToken) {
    }

        public record Token(String authorizationCode, String redirectUri) {
    }
}
