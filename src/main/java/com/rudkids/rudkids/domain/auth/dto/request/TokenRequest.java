package com.rudkids.rudkids.domain.auth.dto.request;

public class TokenRequest {
    private String authorizationCode;
    private String redirectUri;

    private TokenRequest() {
    }

    public TokenRequest(String authorizationCode, String redirectUri) {
        this.authorizationCode = authorizationCode;
        this.redirectUri = redirectUri;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}
