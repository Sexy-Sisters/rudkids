package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthRequest;
import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.infrastructure.dto.AuthUser;
import com.rudkids.core.user.domain.UserRepository;
import com.rudkids.core.user.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenCreator tokenCreator;

    @Transactional
    @Override
    public AuthResponse.AccessAndRefreshToken generateAccessAndRefreshToken(AuthUser.OAuth oAuthUser) {
        var foundUser = userRepository.getUser(oAuthUser);
        var authToken = tokenCreator.createAuthToken(foundUser.getId());
        return new AuthResponse.AccessAndRefreshToken(authToken.getAccessToken(), authToken.getRefreshToken());
    }

    @Override
    public AuthResponse.AccessToken generateRenewalAccessToken(AuthRequest.RenewalToken request) {
        String refreshToken = request.refreshToken();
        var authToken = tokenCreator.renewAuthToken(refreshToken);
        return new AuthResponse.AccessToken(authToken.getAccessToken());
    }

    @Override
    public UUID extractUserId(String accessToken) {
        UUID userId = tokenCreator.extractPayload(accessToken);
        if (!userRepository.existsUser(userId)) {
            throw new NotFoundUserException();
        }
        return userId;
    }

    @Override
    public void validateAdminAuthority(UUID userId) {
        var user = userRepository.getUser(userId);
        user.validateAdminRole();
    }
}
