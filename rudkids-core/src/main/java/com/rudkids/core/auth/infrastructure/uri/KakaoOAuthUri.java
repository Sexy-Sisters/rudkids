package com.rudkids.core.auth.infrastructure.uri;

import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import com.rudkids.core.auth.infrastructure.OAuthProviderType;
import com.rudkids.core.auth.service.OAuthUriGenerator;
import com.rudkids.core.config.properties.KakaoProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@OAuthProvider(OAuthProviderType.KAKAO)
public class KakaoOAuthUri implements OAuthUriGenerator {
    private final KakaoProperties properties;

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
