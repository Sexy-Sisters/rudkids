package com.rudkids.core.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("payment.toss")
@AllArgsConstructor
@Getter
public class TossPaymentProperties {
    private final String clientKey;
    private final String secretKey;
}