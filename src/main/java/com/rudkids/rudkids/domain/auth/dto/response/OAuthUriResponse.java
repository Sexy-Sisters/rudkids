package com.rudkids.rudkids.domain.auth.dto.response;

public class OAuthUriResponse {
    private String oAuthUri;

    private OAuthUriResponse() {
    }

    public OAuthUriResponse(String oAuthUri) {
        this.oAuthUri = oAuthUri;
    }

    public String getoAuthUri() {
        return oAuthUri;
    }
}
