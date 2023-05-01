package com.rudkids.rudkids.infrastructure.oauth.uri;

import com.rudkids.rudkids.domain.auth.OAuthUriGenerator;
import com.rudkids.rudkids.global.config.properties.GoogleProperties;
import com.rudkids.rudkids.infrastructure.oauth.OAuthProvider;
import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleOAuthUri implements OAuthUriGenerator {
    private final GoogleProperties properties;

    @Override
    public boolean isOAuthUri(String provider) {
        return OAuthProvider.isGoogleProvider(provider);
    }

    @Override
    public AuthResponse.OAuthUri generate(String redirectUri) {
        return new AuthResponse.OAuthUri(
            properties.getOAuthEndPoint() + "?"
                + "client_id=" + properties.getClientId() + "&"
                + "redirect_uri=" + redirectUri + "&"
                + "response_type=code&"
                + "scope=" + String.join(" ", properties.getScopes()) + "&"
                + "access_type=" + properties.getAccessType()
        );
    }
}
