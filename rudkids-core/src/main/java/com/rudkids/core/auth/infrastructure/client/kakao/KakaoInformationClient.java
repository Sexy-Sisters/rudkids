package com.rudkids.core.auth.infrastructure.client.kakao;

import com.rudkids.core.auth.infrastructure.dto.kakao.account.KakaoInformationResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "KakaoInformationClient", url = "https://kapi.kakao.com/v2/user/me")
public interface KakaoInformationClient {

    @PostMapping
    KakaoInformationResponse get(@RequestHeader HttpHeaders headers) throws FeignException;
}