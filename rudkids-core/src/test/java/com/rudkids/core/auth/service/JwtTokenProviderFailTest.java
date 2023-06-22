package com.rudkids.core.auth.service;

import com.rudkids.core.auth.exception.ExpiredTokenException;
import com.rudkids.core.auth.exception.InvalidTokenException;
import com.rudkids.core.common.fixtures.JwtTokenProviderFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class JwtTokenProviderFailTest extends JwtTokenProviderFixtures {

    @DisplayName("[Auth-토큰검증-ExpiredTokenException]")
    @Test
    void 엑세스_토큰이_만료되면_예외_발생() {
        JwtTokenProvider expiredJwtTokenProvider = new JwtTokenProvider(
                JwtTokenProviderFixtures.JWT_SECRET_KEY, 0, 0
        );
        String accessToken = expiredJwtTokenProvider.createAccessToken(JwtTokenProviderFixtures.PAYLOAD);

        assertThatThrownBy(() -> expiredJwtTokenProvider.validateToken(accessToken))
                .isInstanceOf(ExpiredTokenException.class);
    }

    @DisplayName("[Auth-토큰검증-ExpiredTokenException]")
    @Test
    void 리프래쉬_토큰이_만료되면_예외_발생() {
        JwtTokenProvider expiredJwtTokenProvider = new JwtTokenProvider(
                JwtTokenProviderFixtures.JWT_SECRET_KEY, 0, 0
        );
        String refreshToken = expiredJwtTokenProvider.createRefreshToken(JwtTokenProviderFixtures.PAYLOAD);

        assertThatThrownBy(() -> expiredJwtTokenProvider.validateToken(refreshToken))
                .isInstanceOf(ExpiredTokenException.class);
    }

    @DisplayName("[Auth-토큰검증-InvalidTokenException]")
    @Test
    void 잘못된_토큰이라면_예외_발생() {
        String invalid = "namsewon";

        assertThatThrownBy(() -> jwtTokenProvider.validateToken(invalid))
                .isInstanceOf(InvalidTokenException.class);
    }
}
