package com.rudkids.rudkids.infrastructure.oauth.uri;

import com.rudkids.rudkids.domain.auth.OAuthUriGenerator;
import com.rudkids.rudkids.global.config.properties.KakaoProperties;
import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOAuthUri implements OAuthUriGenerator {
    private final KakaoProperties properties;

    @Override
    public boolean hasOAuthUri(String provider) {
        return provider.equals("kakao");
    }

    @Override
    public AuthResponse.OAuthUri generate(String redirectUri) {
        return new AuthResponse.OAuthUri(
            properties.getOAuthEndPoint() + "?"
                + "response_type=code&"
                + "client_id=" + properties.getClientId() + "&"
                + "redirect_uri=" + redirectUri
        );
    }
}
