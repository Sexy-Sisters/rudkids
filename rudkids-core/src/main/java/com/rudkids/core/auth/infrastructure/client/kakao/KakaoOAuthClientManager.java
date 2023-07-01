package com.rudkids.core.auth.infrastructure.client.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.auth.service.OAuthClientManager;
import com.rudkids.core.config.properties.KakaoProperties;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import com.rudkids.core.auth.infrastructure.dto.kakao.KakaoTokenRequest;
import com.rudkids.core.auth.infrastructure.dto.kakao.KakaoTokenResponse;
import com.rudkids.core.auth.exception.OAuthException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoOAuthClientManager implements OAuthClientManager {
    private static final String JWT_DELIMITER = "\\.";
    private final KakaoProperties properties;
    private final KakaoTokenClient kakaoTokenClient;
    private final ObjectMapper objectMapper;

    @Override
    public boolean isOAuthClient(String provider) {
        return OAuthProvider.isKakaoProvider(provider);
    }

    @Override
    public AuthUser.OAuth getOAuthUser(String code, String redirectUri) {
        KakaoTokenResponse kakaoTokenResponse = requestToken(code, redirectUri);
        String payload = getPayload(kakaoTokenResponse.getIdToken());
        return parseUserInfo(payload);
    }

    private KakaoTokenResponse requestToken(String code, String redirectUri) {
        KakaoTokenRequest kakaoTokenRequest = KakaoTokenRequest.builder()
            .clientId(properties.getClientId())
            .code(code)
            .redirectUri(redirectUri)
            .build();

        try {
            return kakaoTokenClient.get(kakaoTokenRequest);
        } catch (FeignException e) {
            log.error(e.getMessage());
            throw new OAuthException();
        }
    }

    private String getPayload(String jwt) {
        return jwt.split(JWT_DELIMITER)[1];
    }

    private AuthUser.OAuth parseUserInfo(String payload) {
        String decodedPayload = decodeJwtPayload(payload);
        try {
            return objectMapper.readValue(decodedPayload, AuthUser.OAuth.class);
        } catch (final JsonProcessingException e) {
            throw new OAuthException();
        }
    }

    private String decodeJwtPayload(String payload) {
        return new String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8);
    }
}
