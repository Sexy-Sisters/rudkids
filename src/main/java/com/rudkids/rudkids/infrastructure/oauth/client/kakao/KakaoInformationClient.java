package com.rudkids.rudkids.infrastructure.oauth.client.kakao;

import com.rudkids.rudkids.infrastructure.oauth.dto.kakao.account.KakaoInformationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "KakaoInformationClient", url = "https://kapi.kakao.com/v2/user/me")
public interface KakaoInformationClient {

    @PostMapping
    KakaoInformationResponse get(@RequestHeader HttpHeaders headers);
}