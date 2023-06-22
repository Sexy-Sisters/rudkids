package com.rudkids.core.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("oauth.google")
@Getter
@AllArgsConstructor
public class GoogleProperties {

    private final String clientId;
    private final String clientSecret;
    private final String oAuthEndPoint;
    private final String responseType;
    private final List<String> scopes;
    private final String tokenUri;
    private final String accessType;
    private final String peopleUri;
    private final List<String> personFields;
    private final String key;
}
