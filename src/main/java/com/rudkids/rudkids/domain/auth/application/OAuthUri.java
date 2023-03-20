package com.rudkids.rudkids.domain.auth.application;

@FunctionalInterface
public interface OAuthUri {

    String generate(String redirectUri);
}
