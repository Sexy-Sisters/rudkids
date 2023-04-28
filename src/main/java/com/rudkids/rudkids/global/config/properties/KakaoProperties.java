package com.rudkids.rudkids.global.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("oauth.kakao")
@Getter
public class KakaoProperties {
    private final String clientId;
    private final String oAuthEndPoint;
    private final String tokenUri;
    private final String appAdminKey;
    private final String accountUri;
    private final String clientSecret;

    public KakaoProperties(String clientId,
                           String oAuthEndPoint,
                           String tokenUri,
                           String appAdminKey,
                           String accountUri,
                           String clientSecret) {
        this.clientId = clientId;
        this.oAuthEndPoint = oAuthEndPoint;
        this.tokenUri = tokenUri;
        this.appAdminKey = appAdminKey;
        this.accountUri = accountUri;
        this.clientSecret = clientSecret;
    }
}
