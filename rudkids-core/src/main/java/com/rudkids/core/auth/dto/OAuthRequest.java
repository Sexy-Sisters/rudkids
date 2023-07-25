package com.rudkids.core.auth.dto;

import feign.form.FormProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OAuthRequest {

    @Getter
    @NoArgsConstructor
    public static class GoogleToken {
        @FormProperty("client_id")
        private String clientId;

        @FormProperty("client_secret")
        private String clientSecret;

        @FormProperty("code")
        private String code;

        @FormProperty("grant_type")
        private String grantType;

        @FormProperty("redirect_uri")
        private String redirectUri;

        @Builder
        public GoogleToken(String clientId, String clientSecret, String code, String redirectUri) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.code = code;
            this.grantType = "authorization_code";
            this.redirectUri = redirectUri;
        }
    }


    @NoArgsConstructor
    @Getter
    public static class KakaoToken {
        @FormProperty("client_id")
        private String clientId;

        @FormProperty("code")
        private String code;

        @FormProperty("grant_type")
        private String grantType;

        @FormProperty("redirect_uri")
        private String redirectUri;

        @Builder
        public KakaoToken(String clientId, String code, String redirectUri) {
            this.clientId = clientId;
            this.code = code;
            this.grantType = "authorization_code";
            this.redirectUri = redirectUri;
        }
    }
}
