package com.rudkids.core.auth.annotation;

import com.rudkids.core.auth.exception.OAuthNotFoundException;

import java.util.List;

public class OAuthProviderResolver {

    public static <T> T resolve(List<T> list, String provider) {
        return list.stream()
            .filter(it -> it.getClass().isAnnotationPresent(OAuthProvider.class))
            .filter(it -> isSameOAuthProvider(it.getClass(), provider))
            .findFirst()
            .orElseThrow(OAuthNotFoundException::new);
    }

    private static boolean isSameOAuthProvider(Class<?> providerClass, String provider) {
        var providerType = providerClass.getAnnotation(OAuthProvider.class).value();
        return providerType.isSameDescription(provider);
    }
}