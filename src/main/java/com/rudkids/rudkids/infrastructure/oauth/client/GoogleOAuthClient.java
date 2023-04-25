package com.rudkids.rudkids.infrastructure.oauth.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rudkids.rudkids.domain.auth.OAuthClient;
import com.rudkids.rudkids.global.config.properties.GoogleProperties;
import com.rudkids.rudkids.infrastructure.oauth.dto.Person;
import com.rudkids.rudkids.infrastructure.oauth.dto.UserInfo;
import com.rudkids.rudkids.infrastructure.oauth.exception.NotReadOAuthIdTokenException;
import com.rudkids.rudkids.infrastructure.oauth.dto.GoogleTokenResponse;
import com.rudkids.rudkids.infrastructure.oauth.exception.OAuthException;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
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
    private final Gson gson;

    @Override
    public AuthUser.OAuth getOAuthUser(String code, String redirectUri) {
        GoogleTokenResponse googleTokenResponse = requestGoogleToken(code, redirectUri);
        String payload = getPayload(googleTokenResponse.getIdToken());
        UserInfo userInfo = parseUserInfo(payload);

        ResponseEntity<String> personResponse = requestPerson(googleTokenResponse.getAccessToken());
        Person person = gson.fromJson(personResponse.getBody(), Person.class);
        return AuthUser.OAuth.builder()
                .email(userInfo.email())
                .name(userInfo.name())
                .gender(person.getGender())
                .age(person.getAge())
                .phoneNumber(person.getPhoneNumber())
                .build();
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
        params.add("code", decodeAuthorizationCode(code));
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", redirectUri);
        return params;
    }

    private String decodeAuthorizationCode(String code) {
        return URLDecoder.decode(code, StandardCharsets.UTF_8);
    }

    private GoogleTokenResponse fetchGoogleToken(HttpEntity<MultiValueMap<String, String>> request) {
        try {
            return restTemplate.postForObject(properties.getTokenUri(), request, GoogleTokenResponse.class);
        } catch (final RestClientException e) {
            log.error(e.getMessage());
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
            log.error(e.getMessage());
            throw new NotReadOAuthIdTokenException();
        }
    }

    private String decodeJwtPayload(final String payload) {
        return new String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8);
    }

    private ResponseEntity<String> requestPerson(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        return fetchPerson(request);
    }

    private ResponseEntity<String> fetchPerson(HttpEntity<MultiValueMap<String, String>> request) {
        try {
            return restTemplate.exchange(
                    properties.getPeopleUri(),
                    HttpMethod.GET,
                    request,
                    String.class);
        } catch (final RestClientException e) {
            log.error(e.getMessage());
            throw new OAuthException();
        }
    }
}
