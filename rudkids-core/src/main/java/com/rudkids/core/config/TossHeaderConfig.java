package com.rudkids.core.config;

import com.rudkids.core.config.properties.TossPaymentProperties;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class TossHeaderConfig {
    private final TossPaymentProperties properties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "Basic " + encodeSecretKey());
    }

    private String encodeSecretKey() {
        String key = properties.getSecretKey() + ":";
        byte[] encoded = Base64.getEncoder().encode(key.getBytes(StandardCharsets.UTF_8));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
