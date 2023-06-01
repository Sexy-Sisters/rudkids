package com.rudkids.rudkids.infrastructure.oauth.client.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GoogleInformationClient", url = "https://people.googleapis.com/v1/people/me")
public interface GoogleInformationClient {

    @GetMapping
    String get(
        @RequestHeader HttpHeaders headers,
        @RequestParam(name = "personFields") String personFields,
        @RequestParam(name = "key") String key
    );
}
