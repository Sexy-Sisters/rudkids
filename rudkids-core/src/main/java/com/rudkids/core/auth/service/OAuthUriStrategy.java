package com.rudkids.core.auth.service;

import com.rudkids.core.auth.annotation.OAuthProviderResolver;
import com.rudkids.core.auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuthUriStrategy implements OAuthUri {
    private final List<OAuthUriGenerator> oAuthUriGenerators;

    @Override
    public AuthResponse.Uri generate(String provider, String redirectUri) {
        var generator = OAuthProviderResolver.resolve(oAuthUriGenerators, provider, OAuthUriGenerator.class);
        return generator.generate(redirectUri);
    }
}
