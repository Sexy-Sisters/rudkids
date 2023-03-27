package com.rudkids.rudkids.domain.auth.application;

public class AuthCommand {

    public record RenewalAccessToken(String refreshToken) {
    }

    public record OAuthUser(String email, String name) {
    }
}
