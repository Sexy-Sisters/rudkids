package com.rudkids.rudkids.infrastructure.oauth.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudkids.rudkids.domain.auth.application.OAuthClient;
import com.rudkids.rudkids.domain.auth.dto.OAuthUser;
import com.rudkids.rudkids.global.config.properties.GoogleProperties;
import com.rudkids.rudkids.infrastructure.oauth.dto.GoogleTokenResponse;
import com.rudkids.rudkids.infrastructure.oauth.dto.UserInfo;
import com.rudkids.rudkids.infrastructure.oauth.exception.NotReadOAuthIdTokenException;
import com.rudkids.rudkids.infrastructure.oauth.exception.OAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
@Slf4j
public class GoogleOAuthClient implements OAuthClient {
    private static final String JWT_DELIMITER = "\\.";
    private final GoogleProperties properties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public OAuthUser getOAuthUser(String code, String redirectUri) {
        GoogleTokenResponse googleTokenResponse = requestGoogleToken(code, redirectUri);
        String payload = getPayload(googleTokenResponse.getIdToken());
        UserInfo userInfo = parseUserInfo(payload);
        return new OAuthUser(userInfo.getEmail(), userInfo.getName());
    }

    private GoogleTokenResponse requestGoogleToken(String code, String redirectUri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = generateTokenParams(code, redirectUri);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        return fetchGoogleToken(request);
    }

    private MultiValueMap<String, String> generateTokenParams(final String code, final String redirectUri) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", properties.getClientId());
        params.add("client_secret", properties.getClientSecret());
        params.add("code", URLDecoder.decode(code, StandardCharsets.UTF_8));
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", redirectUri);
        return params;
    }

    private GoogleTokenResponse fetchGoogleToken(HttpEntity<MultiValueMap<String, String>> request) {
        try {
            return restTemplate.postForObject(properties.getTokenUri(), request, GoogleTokenResponse.class);
        } catch (final RestClientException e) {
            log.info(e.getMessage());
            throw new OAuthException();
        }
    }

    private String getPayload(final String jwt) {
        return jwt.split(JWT_DELIMITER)[1];
    }

    private UserInfo parseUserInfo(final String payload) {
        String decodedPayload = decodeJwtPayload(payload);
        try {
            return objectMapper.readValue(decodedPayload, UserInfo.class);
        } catch (final JsonProcessingException e) {
            throw new NotReadOAuthIdTokenException();
        }
    }

    private String decodeJwtPayload(final String payload) {
        return new String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8);
    }
}
