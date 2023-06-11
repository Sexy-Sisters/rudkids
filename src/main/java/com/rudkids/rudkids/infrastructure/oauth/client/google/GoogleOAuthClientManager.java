package com.rudkids.rudkids.infrastructure.oauth.client.google;

import com.google.gson.Gson;
import com.rudkids.rudkids.domain.auth.OAuthClientManager;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.global.config.properties.GoogleProperties;
import com.rudkids.rudkids.infrastructure.oauth.OAuthProvider;
import com.rudkids.rudkids.infrastructure.oauth.dto.google.GoogleTokenRequest;
import com.rudkids.rudkids.infrastructure.oauth.dto.google.GoogleInformationResponse;
import com.rudkids.rudkids.infrastructure.oauth.dto.google.GoogleTokenResponse;
import com.rudkids.rudkids.infrastructure.oauth.exception.OAuthException;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
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
            throw new OAuthException();
        }
    }

    private String getQueryParameters() {
        return String.join(",", properties.getPersonFields());
    }
}
