package com.rudkids.core.auth.domain;

import com.rudkids.core.auth.exception.NoSuchTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AuthTokenTest {

    @DisplayName("같은 리프래쉬 토큰일 경우 통과")
    @Test
    void 같은_리프래쉬_토큰일_경우_통과() {
        AuthToken authToken = new AuthToken("access", "refresh");

        authToken.validateHasSameRefreshToken("refresh");
    }

    @DisplayName("같은 리프래쉬 토큰이 아닐 경우 예외 발생")
    @Test
    void 같은_리프래쉬_토큰이_아닐_경우_예외_발생() {
        AuthToken authToken = new AuthToken("access", "refresh");

        assertThatThrownBy(() -> authToken.validateHasSameRefreshToken("invalid"))
                .isInstanceOf(NoSuchTokenException.class);
    }
}