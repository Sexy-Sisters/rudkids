package com.rudkids.core.auth.infrastructure.client.google;

import com.rudkids.core.auth.dto.OAuthRequest;
import com.rudkids.core.auth.dto.OAuthResponse;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleTokenClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleTokenClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    OAuthResponse.GoogleToken get(OAuthRequest.GoogleToken request) throws FeignException;
}