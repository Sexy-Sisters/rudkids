package com.rudkids.rudkids.domain.auth;

import com.rudkids.rudkids.domain.auth.exception.OAuthNotFoundException;
import com.rudkids.rudkids.interfaces.auth.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OAuthClientStrategy implements OAuthClient {
    private final List<OAuthClientManager> oAuthClientManagers;

    @Override
    public AuthUser.OAuth getOAuthUser(String provider, String code, String redirectUri) {
        var manager = getOAuthClientManager(provider);
        return manager.getOAuthUser(code, redirectUri);
    }

    private OAuthClientManager getOAuthClientManager(String provider) {
        return oAuthClientManagers.stream()
            .filter(manager -> manager.isOAuthClient(provider))
            .findFirst()
            .orElseThrow(OAuthNotFoundException::new);
    }
}
