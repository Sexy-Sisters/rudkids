package com.rudkids.core.auth.annotation;

import com.rudkids.core.auth.exception.OAuthNotFoundException;

import java.util.List;

public class OAuthProviderResolver {

    public static <T> T resolve(List<?> list, String provider, Class<T> responseType) {
        return list.stream()
            .filter(it -> {
                var annotation = it.getClass().getAnnotation(OAuthProvider.class);
                return annotation.value().isSameDescription(provider);
            })
            .findFirst()
            .map(responseType::cast)
            .orElseThrow(OAuthNotFoundException::new);
    }
}