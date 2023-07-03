package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthUriStrategy implements OAuthUri {
    private final Map<OAuthProvider, OAuthUriGenerator> oAuthUriGenerators;

    @Override
    public AuthResponse.Uri generate(String provider, String redirectUri) {
        var generator = oAuthUriGenerators.get(OAuthProvider.toEnum(provider));
        return generator.generate(redirectUri);
    }
}
