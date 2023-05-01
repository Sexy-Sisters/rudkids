package com.rudkids.rudkids.infrastructure.oauth;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OAuthProvider {
    GOOGLE("google"),
    KAKAO("kakao");

    private final String description;

    public static boolean isGoogleProvider(String description) {
        return GOOGLE.description.equals(description);
    }

    public static boolean isKakaoProvider(String description) {
        return GOOGLE.description.equals(description);
    }
}
