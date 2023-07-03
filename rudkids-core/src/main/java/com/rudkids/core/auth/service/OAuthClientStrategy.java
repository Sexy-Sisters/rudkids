package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.auth.infrastructure.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthClientStrategy implements OAuthClient {
    private final Map<OAuthProvider, OAuthClientManager> oAuthClientManagers;

    @Override
    public AuthUser.OAuth getOAuthUser(String provider, String code, String redirectUri) {
        var manager = oAuthClientManagers.get(OAuthProvider.toEnum(provider));
        return manager.getOAuthUser(code, redirectUri);
    }
}