package com.rudkids.core.auth.infrastructure;

import com.rudkids.core.auth.exception.OAuthNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum OAuthProvider {
    GOOGLE("google"),
    KAKAO("kakao");

    private final String description;

    public static OAuthProvider toEnum(String description) {
        return Arrays.stream(values())
            .filter(it -> it.isSameDescription(description))
            .findFirst()
            .orElseThrow(OAuthNotFoundException::new);
    }

    private boolean isSameDescription(String description) {
        return this.description.equals(description);
    }
}
