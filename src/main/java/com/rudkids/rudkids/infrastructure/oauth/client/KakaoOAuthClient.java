package com.rudkids.rudkids.infrastructure.oauth.client;

import com.rudkids.rudkids.domain.auth.OAuthClientManager;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.global.config.properties.KakaoProperties;
import com.rudkids.rudkids.infrastructure.oauth.OAuthProvider;
import com.rudkids.rudkids.infrastructure.oauth.dto.kakao.KakaoTokenResponse;
import com.rudkids.rudkids.infrastructure.oauth.dto.kakao.account.Account;
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

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoOAuthClient implements OAuthClientManager {
    private final KakaoProperties properties;
    private final RestTemplate restTemplate;

    @Override
    public boolean isOAuthClient(String provider) {
        return OAuthProvider.isKakaoProvider(provider);
    }

    @Override
    public AuthUser.OAuth getOAuthUser(String code, String redirectUri) {
        KakaoTokenResponse kakaoTokenResponse = requestKakaoToken(code, redirectUri);
        Account account = requestAccount(kakaoTokenResponse.getAccessToken());

        return AuthUser.OAuth.builder()
            .email(account.getEmail())
            .name(account.getNickname())
            .gender(account.getGender())
            .profileImage(account.getProfileImage())
            .socialType(SocialType.KAKAO)
            .build();
    }

    private KakaoTokenResponse requestKakaoToken(String code, String redirectUri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = generateTokenParams(code, redirectUri);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        return fetchKakaoToken(request);
    }

    private MultiValueMap<String, String> generateTokenParams(final String code, final String redirectUri) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", properties.getClientId());
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", redirectUri);
        return params;
    }

    private KakaoTokenResponse fetchKakaoToken(HttpEntity<MultiValueMap<String, String>> request) {
        try {
            return restTemplate.postForObject(properties.getTokenUri(), request, KakaoTokenResponse.class);
        } catch (final RestClientException e) {
            log.error(e.getMessage());
            throw new OAuthException();
        }
    }

    private Account requestAccount(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        return fetchAccount(request);
    }

    private Account fetchAccount(HttpEntity<MultiValueMap<String, String>> request) {
        try {
            return restTemplate.postForObject(properties.getAccountUri(), request, Account.class);
        } catch (final RestClientException e) {
            log.error(e.getMessage());
            throw new OAuthException();
        }
    }
}
