package com.rudkids.rudkids.global.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sms.api")
@Getter
public class SmsProperties {
    private final String key;
    private final String secret;
    private final String domainUri;

    public SmsProperties(String key, String secret, String domainUri) {
        this.key = key;
        this.secret = secret;
        this.domainUri = domainUri;
    }
}
