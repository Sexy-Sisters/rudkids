package com.rudkids.core.auth.annotation;

import com.rudkids.core.auth.exception.OAuthNotFoundException;

import java.util.List;

/**
 * 이 클래스는 클라이언트가 요청한 OAuth공급자를 찾아 반환하는 클래스입니다.
 * @author Namsewon
 */
public class OAuthProviderResolver {

    /**
     * {@link OAuthProvider @OAuthProvider} 를 적용한 클래스들의 인터페이스 리스트를 받아서 <br>
     * 클라이언트가 요청한 Provider가 적용된 클래스를 찾아서 반환해줍니다.
     * @return oauth provider classType
     */
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