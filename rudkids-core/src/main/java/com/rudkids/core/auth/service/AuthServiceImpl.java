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
@Transactional(readOnly = true)
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

    @Transactional
    @Override
    public void registerInformation(UUID userId, String phoneNumber) {
        var user = userRepository.getUser(userId);
        var generatedPhoneNumber = PhoneNumber.create(phoneNumber);
        user.registerInformation(generatedPhoneNumber);
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

    @Override
    public void validatePhoneNumber(UUID userId) {
        var user = userRepository.getUser(userId);
        user.validateEmptyPhoneNumber();
    }
}
