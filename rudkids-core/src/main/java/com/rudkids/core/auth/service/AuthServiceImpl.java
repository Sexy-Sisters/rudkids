package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthRequest;
import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.user.domain.PhoneNumber;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenCreator tokenCreator;

    @Transactional
    @Override
    public AuthResponse.AccessAndRefreshToken generateAccessAndRefreshToken(AuthUser.OAuth oAuthUser) {
        var foundUser = userRepository.getUser(oAuthUser);
        var authToken = tokenCreator.createAuthToken(foundUser.getId());
        boolean hasPhoneNumber = foundUser.hasPhoneNumber();
        return new AuthResponse.AccessAndRefreshToken(authToken.getAccessToken(), authToken.getRefreshToken(), hasPhoneNumber);
    }

    @Override
    public AuthResponse.AccessToken generateRenewalAccessToken(AuthRequest.RenewalToken request) {
        String refreshToken = request.refreshToken();
        var authToken = tokenCreator.renewAuthToken(refreshToken);
        return new AuthResponse.AccessToken(authToken.getAccessToken());
    }

    @Transactional
    @Override
    public void saveAuthenticatedPhoneNumber(String accessToken, String authenticatedPhoneNumber) {
        UUID userId = tokenCreator.extractPayload(accessToken);
        var user = userRepository.getUser(userId);
        var phoneNumber = PhoneNumber.create(authenticatedPhoneNumber);
        user.updatePhoneNumber(phoneNumber);
    }

    @Override
    public UUID extractUserId(String accessToken) {
        return tokenCreator.extractPayload(accessToken);
    }

    @Override
    public void validateAdminAuthority(UUID userId) {
        var user = userRepository.getUser(userId);
        user.validateAdminRole();
    }
}
