package com.rudkids.rudkids.global.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("oauth.google")
@Getter
public class GoogleProperties {

    private final String clientId;
    private final String clientSecret;
    private final String oAuthEndPoint;
    private final String responseType;
    private final List<String> scopes;
    private final String tokenUri;
    private final String accessType;

    public GoogleProperties(String clientId, String clientSecret, String oAuthEndPoint,
                            String responseType, List<String> scopes, String tokenUri,
                            String accessType) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.oAuthEndPoint = oAuthEndPoint;
        this.responseType = responseType;
        this.scopes = scopes;
        this.tokenUri = tokenUri;
        this.accessType = accessType;
    }
}
