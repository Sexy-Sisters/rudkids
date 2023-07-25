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
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@OAuthProvider(OAuthProviderType.GOOGLE)
public class GoogleOAuthClientManager implements OAuthClientManager {
    private final GoogleProperties properties;
    private final GoogleTokenClient googleTokenClient;
    private final TokenParser tokenParser;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public AuthUser.OAuth getOAuthUser(String code, String redirectUri) {
//        OAuthResponse.GoogleToken tokenResponse = requestToken(code, redirectUri);
        OAuthResponse.GoogleToken tokenResponse = requestGoogleToken(code, redirectUri);
        var userInfo = tokenParser.parse(tokenResponse.getIdToken(), OAuthResponse.GoogleUserInfo.class);
        return new AuthUser.OAuth(userInfo.email(), userInfo.name(), userInfo.picture());
    }

    private OAuthResponse.GoogleToken requestGoogleToken(final String code, final String redirectUri) {
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
        params.add("code", decodeAuthorizationCode(code));
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", redirectUri);
        return params;
    }

    private OAuthResponse.GoogleToken fetchGoogleToken(
        final HttpEntity<MultiValueMap<String, String>> request) {
        try {
            return restTemplate.postForObject("https://oauth2.googleapis.com/token", request, OAuthResponse.GoogleToken.class);
        } catch (final RestClientException e) {
            throw new OAuthException();
        }
    }

//    private OAuthResponse.GoogleToken requestToken(String code, String redirectUri) {
//        OAuthRequest.GoogleToken tokenRequest = OAuthRequest.GoogleToken.builder()
//            .clientId(properties.getClientId())
//            .clientSecret(properties.getClientSecret())
//            .code(decodeAuthorizationCode(code))
//            .redirectUri(redirectUri)
//            .build();
//
//        try {
//            return googleTokenClient.get(tokenRequest);
//        } catch (FeignException e) {
//            log.error(e.getMessage());
//            throw new OAuthException();
//        }
//    }

    private String decodeAuthorizationCode(String code) {
        return URLDecoder.decode(code, StandardCharsets.UTF_8);
    }
}
