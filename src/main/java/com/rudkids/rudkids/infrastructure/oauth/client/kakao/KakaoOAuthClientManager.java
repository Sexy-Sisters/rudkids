package com.rudkids.rudkids.infrastructure.oauth.client.kakao;

import com.rudkids.rudkids.domain.auth.OAuthClientManager;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.global.config.properties.KakaoProperties;
import com.rudkids.rudkids.infrastructure.oauth.OAuthProvider;
import com.rudkids.rudkids.infrastructure.oauth.dto.kakao.KakaoTokenRequest;
import com.rudkids.rudkids.infrastructure.oauth.dto.kakao.KakaoTokenResponse;
import com.rudkids.rudkids.infrastructure.oauth.dto.kakao.account.KakaoInformationResponse;
import com.rudkids.rudkids.infrastructure.oauth.exception.OAuthException;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoOAuthClientManager implements OAuthClientManager {
    private final KakaoProperties properties;
    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoInformationClient kakaoInformationClient;

    @Override
    public boolean isOAuthClient(String provider) {
        return OAuthProvider.isKakaoProvider(provider);
    }

    @Override
    public AuthUser.OAuth getOAuthUser(String code, String redirectUri) {
        KakaoTokenResponse kakaoTokenResponse = requestToken(code, redirectUri);
        KakaoInformationResponse information = requestInformation(kakaoTokenResponse.getAccessToken());

        return AuthUser.OAuth.builder()
            .email(information.getEmail())
            .name(information.getNickname())
            .gender(information.getGender())
            .profileImage(information.getProfileImage())
            .phoneNumber("")
            .socialType(SocialType.KAKAO)
            .build();
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
            throw new OAuthException();
        }
    }

    private KakaoInformationResponse requestInformation(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        try {
            return kakaoInformationClient.get(headers);
        } catch (FeignException e) {
            throw new OAuthException();
        }
    }
}
