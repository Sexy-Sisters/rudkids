package com.rudkids.rudkids.infrastructure.oauth.client.google;

import com.rudkids.rudkids.infrastructure.oauth.dto.google.GoogleTokenRequest;
import com.rudkids.rudkids.infrastructure.oauth.dto.google.GoogleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleTokenClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleTokenClient {

    @PostMapping
    GoogleTokenResponse get(GoogleTokenRequest request);
}