package com.rudkids.core.auth.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 이 어노테이션이 달린 클래스는 OAuth 공급자임을 나타냅니다.
 * @author  Namsewon
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface OAuthProvider {

    /**
     * 어떤 OAuth 공급자를 처리하는지 나타냅니다.
     * @return the oAuthProviderType enum
     */
    OAuthProviderType value();
}