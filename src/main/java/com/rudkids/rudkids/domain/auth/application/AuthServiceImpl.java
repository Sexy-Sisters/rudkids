package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.exception.NotFoundUserException;
import com.rudkids.rudkids.domain.user.repository.UserRepository;
import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;
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
    public AuthResponse.AccessAndRefreshToken generateAccessAndRefreshToken(AuthCommand.OAuthUser oAuthUser) {
        var foundUser = findUser(oAuthUser);
        var authToken = tokenCreator.createAuthToken(foundUser.getId());
        return new AuthResponse.AccessAndRefreshToken(authToken.getAccessToken(), authToken.getRefreshToken());
    }

    private User findUser(AuthCommand.OAuthUser oAuthUser) {
        return userRepository.findByEmail(oAuthUser.email())
                .orElseGet(() -> saveUser(oAuthUser));
    }

    private User saveUser(AuthCommand.OAuthUser oAuthUser) {
        var user = User.builder()
                .email(oAuthUser.email())
                .name(oAuthUser.name())
                .age(null)
                .gender(null)
                .socialType(SocialType.GOOGLE)
                .build();
        return userRepository.save(user);
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
        if(!userRepository.existsById(userId)) {
            throw new NotFoundUserException();
        }
        return userId;
    }
}
