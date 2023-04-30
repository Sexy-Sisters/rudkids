package com.rudkids.rudkids.domain.auth.service;

import com.rudkids.rudkids.domain.auth.AuthCommand;
import com.rudkids.rudkids.domain.auth.TokenCreator;
import com.rudkids.rudkids.domain.user.UserReader;
import com.rudkids.rudkids.domain.user.exception.NotFoundUserException;
import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserReader userReader;
    private final TokenCreator tokenCreator;

    @Transactional
    @Override
    public AuthResponse.AccessAndRefreshToken generateAccessAndRefreshToken(AuthCommand.OAuthUser oAuthUser) {
        var foundUser = userReader.getUser(oAuthUser);
        var authToken = tokenCreator.createAuthToken(foundUser.getId());
        return new AuthResponse.AccessAndRefreshToken(authToken.getAccessToken(), authToken.getRefreshToken());
    }

    @Override
    public AuthResponse.AccessToken generateRenewalAccessToken(AuthCommand.RenewalAccessToken request) {
        String refreshToken = request.refreshToken();
        var authToken = tokenCreator.renewAuthToken(refreshToken);
        return new AuthResponse.AccessToken(authToken.getAccessToken());
    }

    @Override
    public UUID extractUserId(String accessToken) {
        UUID userId = tokenCreator.extractPayload(accessToken);
        if (!userReader.existsUser(userId)) {
            throw new NotFoundUserException();
        }
        return userId;
    }

    @Override
    public void validateAdminAuthority(UUID userId) {
        var user = userReader.getUser(userId);
        user.validateAdminRole();
    }
}
