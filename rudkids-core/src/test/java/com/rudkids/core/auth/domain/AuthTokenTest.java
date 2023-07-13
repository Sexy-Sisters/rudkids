package com.rudkids.core.auth.domain;

import com.rudkids.core.auth.exception.NoSuchTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AuthTokenTest {

    @Nested
    @DisplayName("같은 리프래쉬 토큰인지 검증한다")
    class validateHasSameRefreshToken {

        @DisplayName("성공")
        @Test
        void success() {
            //given
            AuthToken authToken = new AuthToken("access", "refresh");

            //when, then
            authToken.validateHasSameRefreshToken("refresh");
        }

        @Test
        @DisplayName("실패: 다른 리프래쉬 토큰")
        void 같은_리프래쉬_토큰이_아닐_경우_예외_발생한다() {
            //given
            AuthToken authToken = new AuthToken("access", "refresh");

            //when, then
            final String invalidRefreshToken = "invalid";
            assertThatThrownBy(() -> authToken.validateHasSameRefreshToken(invalidRefreshToken))
                .isInstanceOf(NoSuchTokenException.class);
        }
    }

}