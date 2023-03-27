package com.rudkids.rudkids.interfaces.auth.dto;

import lombok.Getter;

public class AuthRequest {

    @Getter
        public record RenewalToken(String refreshToken) {
    }

    @Getter
        public record Token(String authorizationCode, String redirectUri) {
    }
}
