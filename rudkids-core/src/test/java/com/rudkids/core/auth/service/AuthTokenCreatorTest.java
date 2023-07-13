package com.rudkids.core.auth.service;

import com.rudkids.core.auth.domain.AuthToken;
import com.rudkids.core.auth.exception.InvalidTokenException;
import com.rudkids.core.common.fixtures.auth.AuthTokenCreatorFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthTokenCreatorTest extends AuthTokenCreatorFixtures {

    @Nested
    @DisplayName("유저 id로 엑세스 토큰과 래프래쉬 토큰을 생성한다")
    class createAuthToken {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            UUID userId = UUID.randomUUID();

            //when
            AuthToken authToken = tokenCreator.createAuthToken(userId);

            //then
            assertAll(() -> {
                assertThat(authToken.getAccessToken()).isNotEmpty();
                assertThat(authToken.getRefreshToken()).isNotEmpty();
            });
        }

        @Test
        @DisplayName("성공: 이미 가입한 회원이라면 같은 리프래쉬 토큰을 발급한다")
        void success2() {
            //given
            UUID userId = UUID.randomUUID();
            String refreshToken = "refresh";
            tokenRepository.save(userId, refreshToken);

            //when
            AuthToken authToken = tokenCreator.createAuthToken(userId);

            //then
            assertThat(authToken.getRefreshToken()).isEqualTo("refresh");
        }
    }

    @Nested
    @DisplayName("리프래쉬 토큰으로 엑세스 토큰을 재발급한다")
    class renewAuthToken {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            UUID userId = UUID.randomUUID();
            AuthToken saveToken = tokenCreator.createAuthToken(userId);

            //when
            AuthToken authToken = tokenCreator.renewAuthToken(saveToken.getRefreshToken());

            assertAll(() -> {
                assertThat(authToken.getAccessToken()).isNotEmpty();
                assertThat(authToken.getRefreshToken()).isNotEmpty();
                assertThat(authToken.getRefreshToken()).isEqualTo(saveToken.getRefreshToken());
            });
        }
    }

    @Nested
    @DisplayName("토큰에서 페이로드를 추출한다")
    class extractPayload {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            UUID userId = UUID.randomUUID();
            AuthToken authToken = tokenCreator.createAuthToken(userId);

            //when
            UUID actual = tokenCreator.extractPayload(authToken.getAccessToken());

            //then
            assertThat(actual).isEqualTo(userId);
        }

        @Test
        @DisplayName("실패: 잘못된 토큰")
        void fail() {
            //given
            final String invalidToken = "invalid";

            //when, then
            assertThatThrownBy(() -> tokenCreator.extractPayload(invalidToken))
                .isInstanceOf(InvalidTokenException.class);
        }
    }
}