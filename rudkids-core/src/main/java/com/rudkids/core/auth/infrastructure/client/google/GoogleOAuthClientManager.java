package com.rudkids.core.auth.infrastructure.client.google;

import com.google.gson.Gson;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.auth.service.OAuthClientManager;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import com.rudkids.core.auth.infrastructure.dto.google.GoogleTokenRequest;
import com.rudkids.core.auth.infrastructure.dto.google.GoogleInformationResponse;
import com.rudkids.core.auth.infrastructure.dto.google.GoogleTokenResponse;
import com.rudkids.core.auth.exception.OAuthException;
import com.rudkids.core.config.properties.GoogleProperties;
import com.rudkids.core.user.domain.SocialType;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleOAuthClientManager implements OAuthClientManager {
    private final GoogleProperties properties;
    private final GoogleTokenClient googleTokenClient;
    private final GoogleInformationClient googleInformationClient;
    private final Gson gson;

    @Override
    public boolean isOAuthClient(String provider) {
        return OAuthProvider.isGoogleProvider(provider);
    }

    @Override
    public AuthUser.OAuth getOAuthUser(String code, String redirectUri) {
        GoogleTokenResponse googleTokenResponse = requestToken(code, redirectUri);
        String response = requestInformation(googleTokenResponse.getAccessToken());
        GoogleInformationResponse information = gson.fromJson(response, GoogleInformationResponse.class);
        return AuthUser.OAuth.builder()
            .email(information.getEmail())
            .name(information.getName())
            .gender(information.getGender())
            .age(information.getAge())
            .phoneNumber(information.getPhoneNumber())
            .profileImage(information.getPhoto())
            .socialType(SocialType.GOOGLE)
            .build();
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

    private String requestInformation(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        try {
            return googleInformationClient.get(headers, getQueryParameters(), properties.getKey());
        } catch (FeignException e) {
            log.error(e.getMessage());
            throw new OAuthException();
        }
    }

    private String getQueryParameters() {
        return String.join(",", properties.getPersonFields());
    }
}
