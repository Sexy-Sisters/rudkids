package com.rudkids.rudkids.domain.auth;

import lombok.Builder;

public class AuthCommand {

    @Builder
    public record RenewalAccessToken(String refreshToken) {
    }

    @Builder
    public record OAuthUser(String email, String name) {
    }
}
