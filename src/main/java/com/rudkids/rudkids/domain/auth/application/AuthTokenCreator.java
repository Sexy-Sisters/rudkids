package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.domain.auth.application.TokenCreator;
import com.rudkids.rudkids.domain.auth.application.TokenProvider;
import com.rudkids.rudkids.domain.auth.domain.AuthToken;
import com.rudkids.rudkids.domain.auth.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthTokenCreator implements TokenCreator {
    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;

    @Override
    public AuthToken createAuthToken(UUID userId) {
        String accessToken = tokenProvider.createAccessToken(String.valueOf(userId));
        String refreshToken = createRefreshToken(userId);
        return new AuthToken(accessToken, refreshToken);
    }

    private String createRefreshToken(UUID userId) {
        return tokenRepository.findByUserId(userId)
                .orElseGet(() -> {
                    String refreshToken = tokenProvider.createRefreshToken();
                    return tokenRepository.save(userId, refreshToken);
                });
    }

    @Override
    public AuthToken renewAuthToken(String refreshToken) {
        tokenProvider.validateToken(refreshToken);
        UUID userId = UUID.fromString(tokenProvider.getPayload(refreshToken));

        String accessTokenForRenew = tokenProvider.createAccessToken(String.valueOf(userId));
        AuthToken renewalAuthToken = new AuthToken(accessTokenForRenew, refreshToken);
        renewalAuthToken.validateHasSameRefreshToken(refreshToken);
        return renewalAuthToken;
    }

    @Override
    public UUID extractPayload(String accessToken) {
        tokenProvider.validateToken(accessToken);
        return UUID.fromString(tokenProvider.getPayload(accessToken));
    }
}
