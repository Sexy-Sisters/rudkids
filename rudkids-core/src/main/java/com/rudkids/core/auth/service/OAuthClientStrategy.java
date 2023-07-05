package com.rudkids.core.auth.service;

import com.rudkids.core.auth.annotation.OAuthProviderResolver;
import com.rudkids.core.auth.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuthClientStrategy implements OAuthClient {
    private final List<OAuthClientManager> oAuthClientManagers;

    @Override
    public AuthUser.OAuth getOAuthUser(String provider, String code, String redirectUri) {
        var manager = OAuthProviderResolver.resolve(oAuthClientManagers, provider);
        return manager.getOAuthUser(code, redirectUri);
    }
}