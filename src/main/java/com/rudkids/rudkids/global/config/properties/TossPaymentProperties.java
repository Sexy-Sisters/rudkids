package com.rudkids.rudkids.global.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("payment.toss")
@Getter
public class TossPaymentProperties {
    private final String clientKey;
    private final String secretKey;
    private final String confirmUri;

    public TossPaymentProperties(String clientKey,
                                 String secretKey,
                                 String confirmUri) {
        this.clientKey = clientKey;
        this.secretKey = secretKey;
        this.confirmUri = confirmUri;
    }
}
