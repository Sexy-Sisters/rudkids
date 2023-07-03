package com.rudkids.core.auth.infrastructure.client.kakao;

import com.rudkids.core.auth.dto.OAuthRequest;
import com.rudkids.core.auth.dto.OAuthResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "KakaoTokenClient", url = "https://kauth.kakao.com/oauth/token")
public interface KakaoTokenClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    OAuthResponse.KakaoToken get(OAuthRequest.KakaoToken request) throws FeignException;
}