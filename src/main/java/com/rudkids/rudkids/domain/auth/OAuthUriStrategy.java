package com.rudkids.rudkids.domain.auth;

import com.rudkids.rudkids.domain.auth.exception.OAuthNotFoundException;
import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuthUriStrategy implements OAuthUri {
    private final List<OAuthUriGenerator> oAuthUriGenerators;

    @Override
    public AuthResponse.OAuthUri generate(String provider, String redirectUri) {
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
