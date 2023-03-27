package com.rudkids.rudkids.domain.auth.application;

import lombok.Builder;
import lombok.Getter;

public class AuthCommand {

    @Getter
    @Builder
        public record RenewalToken(String refreshToken) {
    }

    @Getter
    @Builder
        public record OAuthUser(String email, String name) {
    }
}
