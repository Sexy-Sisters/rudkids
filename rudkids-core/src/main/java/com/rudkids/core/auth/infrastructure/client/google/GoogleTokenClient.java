package com.rudkids.core.auth.infrastructure.client.google;

import com.rudkids.core.auth.infrastructure.dto.google.GoogleTokenRequest;
import com.rudkids.core.auth.infrastructure.dto.google.GoogleTokenResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleTokenClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleTokenClient {

    @PostMapping
    GoogleTokenResponse get(GoogleTokenRequest request) throws FeignException;
}