package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.domain.auth.dto.OAuthUser;
import com.rudkids.rudkids.domain.auth.domain.AuthToken;
import com.rudkids.rudkids.domain.auth.domain.OAuthToken;
import com.rudkids.rudkids.domain.auth.dto.request.TokenRenewalRequest;
import com.rudkids.rudkids.domain.auth.dto.response.AccessAndRefreshTokenResponse;
import com.rudkids.rudkids.domain.auth.dto.response.AccessTokenResponse;
import com.rudkids.rudkids.domain.auth.repository.OAuthTokenRepository;
import com.rudkids.rudkids.domain.user.domain.Email;
import com.rudkids.rudkids.domain.user.domain.Name;
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
public class AuthService {
    private final UserRepository userRepository;
    private final OAuthTokenRepository oAuthTokenRepository;
    private final TokenCreator tokenCreator;

    public AccessAndRefreshTokenResponse generateAccessAndRefreshToken(OAuthUser oAuthUser) {
        User foundUser = findUser(oAuthUser);

        OAuthToken oAuthToken = getOAuthToken(oAuthUser, foundUser);
        oAuthToken.checkRefreshToken(oAuthToken.getRefreshToken());
        AuthToken authToken = tokenCreator.createAuthToken(foundUser.getId());
        return new AccessAndRefreshTokenResponse(authToken.getAccessToken(), authToken.getRefreshToken());
    }

    private User findUser(OAuthUser oAuthUser) {
        return userRepository.findByEmailValue(oAuthUser.getEmail())
                .orElseGet(() -> saveUser(oAuthUser));
    }

    private User saveUser(OAuthUser oAuthUser) {
        User user = User.create(
                Email.create(oAuthUser.getEmail()),
                Name.create(oAuthUser.getName()),
                SocialType.GOOGLE
        );
        return userRepository.save(user);
    }

    private OAuthToken getOAuthToken(final OAuthUser oAuthUser, final User user) {
        UUID userId = user.getId();
        return oAuthTokenRepository.findById(userId)
                .orElseGet(() -> {
                    OAuthToken oAuthToken = new OAuthToken(user, oAuthUser.getRefreshToken());
                    return oAuthTokenRepository.save(oAuthToken);
                });
    }

    public AccessTokenResponse generateRenewalAccessToken(TokenRenewalRequest tokenRenewalRequest) {
        String refreshToken = tokenRenewalRequest.getRefreshToken();
        AuthToken authToken = tokenCreator.renewAuthToken(refreshToken);
        return new AccessTokenResponse(authToken.getAccessToken());
    }

    public UUID extractUserId(String accessToken) {
        UUID userId = tokenCreator.extractPayload(accessToken);
        if(!userRepository.existsById(userId)) {
            throw new NotFoundUserException();
        }
        return userId;
    }
}
