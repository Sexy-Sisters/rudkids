package com.rudkids.rudkids.global.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("payment.toss")
@RequiredArgsConstructor
@Getter
public class TossPaymentProperties {
    private final String clientKey;
    private final String secretKey;
    private final String confirmUri;
}