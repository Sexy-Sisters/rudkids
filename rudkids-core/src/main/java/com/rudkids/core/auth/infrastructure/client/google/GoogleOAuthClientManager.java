package com.rudkids.core.auth.infrastructure.client.google;

import com.rudkids.core.auth.dto.OAuthRequest;
import com.rudkids.core.auth.dto.OAuthResponse;
import com.rudkids.core.auth.annotation.OAuthProvider;
import com.rudkids.core.auth.annotation.OAuthProviderType;
import com.rudkids.core.auth.infrastructure.client.TokenParser;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.auth.service.OAuthClientManager;
import com.rudkids.core.auth.exception.OAuthException;
import com.rudkids.core.config.properties.GoogleProperties;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@OAuthProvider(OAuthProviderType.GOOGLE)
public class GoogleOAuthClientManager implements OAuthClientManager {
    private final GoogleProperties properties;
    private final GoogleTokenClient googleTokenClient;
    private final TokenParser tokenParser;

    @Override
    public AuthUser.OAuth getOAuthUser(String code, String redirectUri) {
        OAuthResponse.GoogleToken tokenResponse = requestToken(code, redirectUri);
        var userInfo = tokenParser.parse(tokenResponse.getIdToken(), OAuthResponse.GoogleUserInfo.class);
        return new AuthUser.OAuth(userInfo.email(), userInfo.name(), userInfo.picture());
    }

    private OAuthResponse.GoogleToken requestToken(String code, String redirectUri) {
        OAuthRequest.GoogleToken tokenRequest = OAuthRequest.GoogleToken.builder()
            .clientId(properties.getClientId())
            .clientSecret(properties.getClientSecret())
            .code(decodeAuthorizationCode(code))
            .redirectUri(redirectUri)
            .build();

        try {
            return googleTokenClient.get(tokenRequest);
        } catch (FeignException e) {
            log.error(e.getMessage());
            throw new OAuthException();
        }
    }

    private String decodeAuthorizationCode(String code) {
        return URLDecoder.decode(code, StandardCharsets.UTF_8);
    }
}
