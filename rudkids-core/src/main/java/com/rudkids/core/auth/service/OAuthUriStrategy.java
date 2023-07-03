package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.exception.OAuthNotFoundException;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuthUriStrategy implements OAuthUri {
    private final List<OAuthUriGenerator> oAuthUriGenerators;

    @Override
    public AuthResponse.Uri generate(String provider, String redirectUri) {
        var generator = findByAnnotation(provider);
        return generator.generate(redirectUri);
    }

    private OAuthUriGenerator findByAnnotation(String provider) {
        return oAuthUriGenerators.stream()
            .filter(it -> {
                var annotation = it.getClass().getAnnotation(OAuthProvider.class);
                return annotation.value().isSameDescription(provider);
            })
            .findFirst()
            .orElseThrow(OAuthNotFoundException::new);
    }
}
