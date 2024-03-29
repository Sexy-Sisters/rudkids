package com.rudkids.core.config;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
public class ExpiringMapConfig {

    /*
    전화번호 인증코드 map
     */
    @Bean
    public Map<String, String> codeRepository() {
        return ExpiringMap.builder()
            .maxSize(1000)
            .expirationPolicy(ExpirationPolicy.CREATED)
            .expiration(3, TimeUnit.MINUTES)
            .build();
    }

    /*
    Auth 토큰 map
     */
    @Bean
    public Map<UUID, String> tokenRepository() {
        return ExpiringMap.builder()
            .maxSize(1000)
            .expirationPolicy(ExpirationPolicy.CREATED)
            .expiration(14, TimeUnit.DAYS)
            .build();
    }
}
