package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.auth.exception.OAuthNotFoundException;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuthClientStrategy implements OAuthClient {
    private final List<OAuthClientManager> oAuthClientManagers;

    @Override
    public AuthUser.OAuth getOAuthUser(String provider, String code, String redirectUri) {
        var manager = findByAnnotation(provider);
        return manager.getOAuthUser(code, redirectUri);
    }

    private OAuthClientManager findByAnnotation(String provider) {
        return oAuthClientManagers.stream()
            .filter(it -> {
                var annotation = it.getClass().getAnnotation(OAuthProvider.class);
                return annotation.value().isSameDescription(provider);
            })
            .findFirst()
            .orElseThrow(OAuthNotFoundException::new);
    }
}