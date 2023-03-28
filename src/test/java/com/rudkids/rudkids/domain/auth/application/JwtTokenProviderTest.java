package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.auth.exception.ExpiredTokenException;
import com.rudkids.rudkids.domain.auth.exception.InvalidTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@ServiceTest
class JwtTokenProviderTest {
    private static final String JWT_SECRET_KEY = "a".repeat(32); // Secret Key는 최소 32바이트 이상이어야함.
    private static final int ACCESS_TOKEN_EXPIRE_TIME = 3600;
    private static final int REFRESH_TOKEN_EXPIRE_TIME = 3600;
    private static final String PAYLOAD = "payload";

    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(
            JWT_SECRET_KEY,
            ACCESS_TOKEN_EXPIRE_TIME,
            REFRESH_TOKEN_EXPIRE_TIME
    );

    @DisplayName("에세스 토큰 생성")
    @Test
    void 에세스_토큰_생성() {
        String accessToken = jwtTokenProvider.createAccessToken(PAYLOAD);

        assertThat(accessToken.split("\\.")).hasSize(3);
    }

    @DisplayName("리프래쉬 토큰 생성")
    @Test
    void 리프래쉬_토큰_생성() {
        String refreshToken = jwtTokenProvider.createRefreshToken(PAYLOAD);

        assertThat(refreshToken.split("\\.")).hasSize(3);
    }

    @DisplayName("페이로드를 가져온다.")
    @Test
    void 페이로드를_가져온다() {
        String accessToken = jwtTokenProvider.createAccessToken(PAYLOAD);

        String payload = jwtTokenProvider.getPayload(accessToken);

        assertThat(payload).isEqualTo(PAYLOAD);
    }

    @DisplayName("에세스 토큰이 만료되면 예외 발생")
    @Test
    void 에세스_토큰이_만료되면_예외_발생() {
        JwtTokenProvider expiredJwtTokenProvider = new JwtTokenProvider(
                JWT_SECRET_KEY, 0, 0
        );
        String accessToken = expiredJwtTokenProvider.createAccessToken(PAYLOAD);

        assertThatThrownBy(() -> expiredJwtTokenProvider.validateToken(accessToken))
                .isInstanceOf(ExpiredTokenException.class);
    }

    @DisplayName("리프래쉬 토큰이 만료되면 예외 발생")
    @Test
    void 리프래쉬_토큰이_만료되면_예외_발생() {
        JwtTokenProvider expiredJwtTokenProvider = new JwtTokenProvider(
                JWT_SECRET_KEY, 0, 0
        );
        String refreshToken = expiredJwtTokenProvider.createRefreshToken(PAYLOAD);

        assertThatThrownBy(() -> expiredJwtTokenProvider.validateToken(refreshToken))
                .isInstanceOf(ExpiredTokenException.class);
    }

    @DisplayName("잘못된 토큰이라면 예외 발생")
    @Test
    void 잘못된_토큰이라면_예외_발생() {
        String invalid = "namsewon";

        assertThatThrownBy(() -> jwtTokenProvider.validateToken(invalid))
                .isInstanceOf(InvalidTokenException.class);
    }
}