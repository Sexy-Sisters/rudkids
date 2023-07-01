package com.rudkids.core.auth.infrastructure.client.google;

import com.rudkids.core.auth.infrastructure.dto.google.GoogleTokenRequest;
import com.rudkids.core.auth.infrastructure.dto.google.GoogleTokenResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleTokenClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleTokenClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    GoogleTokenResponse get(GoogleTokenRequest request) throws FeignException;
}