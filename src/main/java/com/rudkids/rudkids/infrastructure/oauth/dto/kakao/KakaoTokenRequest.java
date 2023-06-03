package com.rudkids.rudkids.infrastructure.oauth.dto.kakao;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class KakaoTokenRequest {

    @FormProperty("client_id")
    private String clientId;

    @FormProperty("code")
    private String code;

    @FormProperty("grant_type")
    private String grantType;

    @FormProperty("redirect_uri")
    private String redirectUri;

    @Builder
    public KakaoTokenRequest(String clientId, String code, String redirectUri) {
        this.clientId = clientId;
        this.code = code;
        this.grantType = "authorization_code";
        this.redirectUri = redirectUri;
    }
}