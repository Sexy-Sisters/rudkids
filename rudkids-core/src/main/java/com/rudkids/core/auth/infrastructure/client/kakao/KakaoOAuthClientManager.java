package com.rudkids.core.auth.infrastructure.client.kakao;

import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.auth.service.OAuthClientManager;
import com.rudkids.core.config.properties.KakaoProperties;
import com.rudkids.core.user.domain.SocialType;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import com.rudkids.core.auth.infrastructure.dto.kakao.KakaoTokenRequest;
import com.rudkids.core.auth.infrastructure.dto.kakao.KakaoTokenResponse;
import com.rudkids.core.auth.infrastructure.dto.kakao.account.KakaoInformationResponse;
import com.rudkids.core.auth.exception.OAuthException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
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
            log.error(e.getMessage());
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
            log.error(e.getMessage());
            throw new OAuthException();
        }
    }
}
