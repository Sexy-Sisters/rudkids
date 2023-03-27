package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.domain.auth.domain.AuthToken;
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
        User foundUser = findUser(oAuthUser);
        AuthToken authToken = tokenCreator.createAuthToken(foundUser.getId());
        return new AuthResponse.AccessAndRefreshToken(authToken.getAccessToken(), authToken.getRefreshToken());
    }

    private User findUser(AuthCommand.OAuthUser oAuthUser) {
        return userRepository.findByEmail(oAuthUser.getEmail())
                .orElseGet(() -> saveUser(oAuthUser));
    }

    private User saveUser(AuthCommand.OAuthUser oAuthUser) {
        User user = User.builder()
                .email(oAuthUser.getEmail())
                .name(oAuthUser.getName())
                .age(null)
                .gender(null)
                .socialType(SocialType.GOOGLE)
                .build();
        return userRepository.save(user);
    }

    @Override
    public AuthResponse.AccessToken generateRenewalAccessToken(AuthCommand.RenewalToken tokenRenewalRequest) {
        String refreshToken = tokenRenewalRequest.getRefreshToken();
        AuthToken authToken = tokenCreator.renewAuthToken(refreshToken);
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
