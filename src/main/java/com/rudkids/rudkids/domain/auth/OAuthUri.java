package com.rudkids.rudkids.domain.auth;

@FunctionalInterface
public interface OAuthUri {

    String generate(String redirectUri);
}
