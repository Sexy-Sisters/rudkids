package com.rudkids.core.auth.infrastructure;

import com.rudkids.core.auth.domain.OAuthToken;
import com.rudkids.core.auth.domain.OAuthTokenRepository;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.order.exception.NoSuchOAuthTokenException;
import com.rudkids.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthTokenRepositoryImpl implements OAuthTokenRepository {
    private final JpaOAuthTokenRepository oAuthTokenRepository;

    @Override
    public OAuthToken get(User user) {
        return oAuthTokenRepository.findByUser(user)
            .orElseThrow(NoSuchOAuthTokenException::new);
    }

    @Override
    public OAuthToken getOrCreate(User user, AuthUser.OAuth oAuthUser) {
        return oAuthTokenRepository.findByUser(user)
            .orElseGet(() -> saveOAuthToken(user, oAuthUser.refreshToken()));
    }

    private OAuthToken saveOAuthToken(User user, String refreshToken) {
        var oAuthToken = OAuthToken.create(user, refreshToken);
        return oAuthTokenRepository.save(oAuthToken);
    }
}
