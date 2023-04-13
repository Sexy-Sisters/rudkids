package com.rudkids.rudkids.infrastructure.oauth.uri;

import com.rudkids.rudkids.domain.auth.OAuthUri;
import com.rudkids.rudkids.global.config.properties.GoogleProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleOAuthUri implements OAuthUri {
    private final GoogleProperties properties;

    @Override
    public String generate(String redirectUri) {
        return properties.getOAuthEndPoint() + "?"
                + "client_id=" + properties.getClientId() + "&"
                + "redirect_uri=" + redirectUri + "&"
                + "response_type=code&"
                + "scope=" + String.join(" ", properties.getScopes()) + "&"
                + "access_type=" + properties.getAccessType();
    }
}
