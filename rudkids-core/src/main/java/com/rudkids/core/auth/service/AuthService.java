package com.rudkids.core.auth.service;

import com.rudkids.core.auth.domain.OAuthTokenRepository;
import com.rudkids.core.auth.dto.AuthRequest;
import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.user.domain.PhoneNumber;
import com.rudkids.core.user.domain.UserRepository;
import com.rudkids.core.user.exception.PhoneNumberEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final OAuthTokenRepository oAuthTokenRepository;
    private final UserRepository userRepository;
    private final TokenCreator tokenCreator;

    @Transactional
    public AuthResponse.AccessAndRefreshToken generateAccessAndRefreshToken(AuthUser.OAuth oAuthUser) {
        var foundUser = userRepository.getUser(oAuthUser);

        var oAuthToken = oAuthTokenRepository.getOrCreate(foundUser, oAuthUser);
        oAuthToken.change(oAuthUser.refreshToken());

        var authToken = tokenCreator.createAuthToken(foundUser.getId());
        boolean hasPhoneNumber = foundUser.hasPhoneNumber();
        return new AuthResponse.AccessAndRefreshToken(authToken.getAccessToken(), authToken.getRefreshToken(), hasPhoneNumber);
    }

    public AuthResponse.AccessToken generateRenewalAccessToken(AuthRequest.RenewalToken request) {
        String refreshToken = request.refreshToken();
        var authToken = tokenCreator.renewAuthToken(refreshToken);
        return new AuthResponse.AccessToken(authToken.getAccessToken());
    }

    @Transactional
    public void saveAuthenticatedPhoneNumber(UUID userId, String authenticatedPhoneNumber) {
        var user = userRepository.getUser(userId);
        var phoneNumber = PhoneNumber.create(authenticatedPhoneNumber);
        user.updatePhoneNumber(phoneNumber);
    }

    public UUID extractUserId(String accessToken) {
        return tokenCreator.extractPayload(accessToken);
    }

    public void validateAdminAuthority(UUID userId) {
        var user = userRepository.getUser(userId);
        user.validateAdminRole();
    }

    public void validateHasPhoneNumber(UUID userId) {
        var user = userRepository.getUser(userId);

        if(!user.hasPhoneNumber()) {
            throw new PhoneNumberEmptyException();
        }
    }
}