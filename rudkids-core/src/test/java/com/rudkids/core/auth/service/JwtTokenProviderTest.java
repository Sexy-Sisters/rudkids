package com.rudkids.core.auth.service;

import com.rudkids.core.auth.exception.ExpiredTokenException;
import com.rudkids.core.auth.exception.InvalidTokenException;
import com.rudkids.core.common.fixtures.auth.JwtTokenProviderFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class JwtTokenProviderTest extends JwtTokenProviderFixtures {

    @Nested
    @DisplayName("엑세스 토큰을 생성한다")
    class createAccessToken {

        @Test
        @DisplayName("성공")
        void success() {
            //given

            //when
            String accessToken = jwtTokenProvider.createAccessToken(JwtTokenProviderFixtures.PAYLOAD);

            //then
            assertThat(accessToken.split("\\.")).hasSize(3);
        }
    }

    @Nested
    @DisplayName("리프래쉬 토큰을 생성한다")
    class createRefreshToken {

        @Test
        @DisplayName("성공")
        void success() {
            //given

            //when
            String refreshToken = jwtTokenProvider.createRefreshToken(JwtTokenProviderFixtures.PAYLOAD);

            //then
            assertThat(refreshToken.split("\\.")).hasSize(3);
        }
    }

    @Nested
    @DisplayName("토큰을 검증한다")
    class validateToken {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            String accessToken = jwtTokenProvider.createAccessToken(JwtTokenProviderFixtures.PAYLOAD);

            //when, then
            jwtTokenProvider.validateToken(accessToken);
        }

        @Test
        @DisplayName("실패: 만료된 토큰")
        void fail() {
            //given
            JwtTokenProvider expiredJwtTokenProvider = new JwtTokenProvider(
                JwtTokenProviderFixtures.JWT_SECRET_KEY, 0, 0
            );
            String accessToken = expiredJwtTokenProvider.createAccessToken(JwtTokenProviderFixtures.PAYLOAD);

            //when, then
            assertThatThrownBy(() -> expiredJwtTokenProvider.validateToken(accessToken))
                .isInstanceOf(ExpiredTokenException.class);
        }

        @Test
        @DisplayName("실패: 잘못된 토큰")
        void fail2() {
            //given
            String invalid = "invalid";

            //when, then
            assertThatThrownBy(() -> jwtTokenProvider.validateToken(invalid))
                .isInstanceOf(InvalidTokenException.class);
        }
    }

    @Nested
    @DisplayName("페이로드를 추출한다")
    class getPayload {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            String accessToken = jwtTokenProvider.createAccessToken(JwtTokenProviderFixtures.PAYLOAD);

            //when
            String payload = jwtTokenProvider.getPayload(accessToken);

            //then
            assertThat(payload).isEqualTo(JwtTokenProviderFixtures.PAYLOAD);
        }
    }
}