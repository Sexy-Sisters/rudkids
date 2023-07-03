package com.rudkids.core.auth.infrastructure.uri;

import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.annotation.OAuthProvider;
import com.rudkids.core.auth.annotation.OAuthProviderType;
import com.rudkids.core.auth.service.OAuthUriGenerator;
import com.rudkids.core.config.properties.GoogleProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@OAuthProvider(OAuthProviderType.GOOGLE)
public class GoogleOAuthUri implements OAuthUriGenerator {
    private final GoogleProperties properties;

    @Override
    public AuthResponse.Uri generate(String redirectUri) {
        return new AuthResponse.Uri(
            properties.getOAuthEndPoint() + "?"
                + "client_id=" + properties.getClientId() + "&"
                + "redirect_uri=" + redirectUri + "&"
                + "response_type=code&"
                + "scope=" + String.join(" ", properties.getScopes()) + "&"
                + "access_type=" + properties.getAccessType()
        );
    }
}
