package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.domain.auth.dto.OAuthUser;

public interface OAuthClient {
    OAuthUser getOAuthUser(String code, String redirectUri);
}
