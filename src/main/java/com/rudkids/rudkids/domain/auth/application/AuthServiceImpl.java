package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.domain.auth.dto.OAuthUser;
import com.rudkids.rudkids.domain.auth.domain.AuthToken;
import com.rudkids.rudkids.domain.auth.dto.request.TokenRenewalRequest;
import com.rudkids.rudkids.domain.auth.dto.response.AccessAndRefreshTokenResponse;
import com.rudkids.rudkids.domain.auth.dto.response.AccessTokenResponse;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.exception.NotFoundUserException;
import com.rudkids.rudkids.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenCreator tokenCreator;

    @Override
    public AccessAndRefreshTokenResponse generateAccessAndRefreshToken(OAuthUser oAuthUser) {
        User foundUser = findUser(oAuthUser);
        AuthToken authToken = tokenCreator.createAuthToken(foundUser.getId());
        return new AccessAndRefreshTokenResponse(authToken.getAccessToken(), authToken.getRefreshToken());
    }

    private User findUser(OAuthUser oAuthUser) {
        return userRepository.findByEmail(oAuthUser.getEmail())
                .orElseGet(() -> saveUser(oAuthUser));
    }

    private User saveUser(OAuthUser oAuthUser) {
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
    public AccessTokenResponse generateRenewalAccessToken(TokenRenewalRequest tokenRenewalRequest) {
        String refreshToken = tokenRenewalRequest.getRefreshToken();
        AuthToken authToken = tokenCreator.renewAuthToken(refreshToken);
        return new AccessTokenResponse(authToken.getAccessToken());
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
