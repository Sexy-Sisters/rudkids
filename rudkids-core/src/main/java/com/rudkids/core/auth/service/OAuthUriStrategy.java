package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.exception.OAuthNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuthUriStrategy implements OAuthUri {
    private final List<OAuthUriGenerator> oAuthUriGenerators;

    @Override
    public AuthResponse.Uri generate(String provider, String redirectUri) {
        var generator = getOAuthGenerator(provider);
        return generator.generate(redirectUri);
    }
    
    private OAuthUriGenerator getOAuthGenerator(String provider) {
        return oAuthUriGenerators.stream()
            .filter(generator -> generator.isOAuthUri(provider))
            .findFirst()
            .orElseThrow(OAuthNotFoundException::new);
    }
}
