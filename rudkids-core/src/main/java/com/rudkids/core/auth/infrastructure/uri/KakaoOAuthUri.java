package com.rudkids.core.auth.infrastructure.uri;

import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.service.OAuthUriGenerator;
import com.rudkids.core.config.properties.KakaoProperties;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOAuthUri implements OAuthUriGenerator {
    private final KakaoProperties properties;

    @Override
    public boolean isOAuthUri(String provider) {
        return OAuthProvider.isKakaoProvider(provider);
    }

    @Override
    public AuthResponse.Uri generate(String redirectUri) {
        return new AuthResponse.Uri(
            properties.getOAuthEndPoint() + "?"
                + "response_type=code&"
                + "client_id=" + properties.getClientId() + "&"
                + "redirect_uri=" + redirectUri
        );
    }
}
