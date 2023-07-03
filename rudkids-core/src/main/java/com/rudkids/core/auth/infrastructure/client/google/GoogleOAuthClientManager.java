package com.rudkids.core.auth.infrastructure.client.google;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.auth.infrastructure.dto.google.GoogleUserInfo;
import com.rudkids.core.auth.service.OAuthClientManager;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import com.rudkids.core.auth.infrastructure.dto.google.GoogleTokenRequest;
import com.rudkids.core.auth.infrastructure.dto.google.GoogleTokenResponse;
import com.rudkids.core.auth.exception.OAuthException;
import com.rudkids.core.config.properties.GoogleProperties;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleOAuthClientManager implements OAuthClientManager {
    private static final String JWT_DELIMITER = "\\.";
    private final GoogleProperties properties;
    private final GoogleTokenClient googleTokenClient;
    private final ObjectMapper objectMapper;

    @Override
    public boolean isOAuthClient(String provider) {
        return OAuthProvider.isGoogleProvider(provider);
    }

    @Override
    public AuthUser.OAuth getOAuthUser(String code, String redirectUri) {
        GoogleTokenResponse googleTokenResponse = requestToken(code, redirectUri);
        String payload = getPayload(googleTokenResponse.getIdToken());
        var userInfo = parseUserInfo(payload);
        return new AuthUser.OAuth(userInfo.email(), userInfo.name(), userInfo.picture());
    }

    private GoogleTokenResponse requestToken(String code, String redirectUri) {
        GoogleTokenRequest googleTokenRequest = GoogleTokenRequest.builder()
            .clientId(properties.getClientId())
            .clientSecret(properties.getClientSecret())
            .code(decodeAuthorizationCode(code))
            .redirectUri(redirectUri)
            .build();

        try {
            return googleTokenClient.get(googleTokenRequest);
        } catch (FeignException e) {
            log.error(e.getMessage());
            throw new OAuthException();
        }
    }

    private String decodeAuthorizationCode(String code) {
        return URLDecoder.decode(code, StandardCharsets.UTF_8);
    }

    private String getPayload(String jwt) {
        return jwt.split(JWT_DELIMITER)[1];
    }

    private GoogleUserInfo parseUserInfo(String payload) {
        String decodedPayload = decodeJwtPayload(payload);
        try {
            return objectMapper.readValue(decodedPayload, GoogleUserInfo.class);
        } catch (final JsonProcessingException e) {
            throw new OAuthException();
        }
    }

    private String decodeJwtPayload(String payload) {
        return new String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8);
    }
}
