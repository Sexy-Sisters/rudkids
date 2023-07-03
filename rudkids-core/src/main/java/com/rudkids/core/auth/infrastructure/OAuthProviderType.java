package com.rudkids.core.auth.infrastructure;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OAuthProviderType {
    GOOGLE("google"),
    KAKAO("kakao");

    private final String description;

    public boolean isSameDescription(String description) {
        return this.description.equals(description);
    }
}