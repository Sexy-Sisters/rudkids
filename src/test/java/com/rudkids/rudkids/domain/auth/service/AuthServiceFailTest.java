package com.rudkids.rudkids.domain.auth.service;

import com.rudkids.rudkids.common.fixtures.auth.AuthServiceFixtures;
import com.rudkids.rudkids.domain.auth.AuthCommand;
import com.rudkids.rudkids.domain.auth.exception.InvalidTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AuthServiceFailTest extends AuthServiceFixtures {

    @DisplayName("리프래쉬 토큰으로 새로운 엑세스 토큰을 발급할 때 존재하지 않거나 잘못된 리프래쉬 토큰이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "invalid"})
    void 리프래쉬_토큰으로_새로운_엑세스_토큰을_발급할_때_존재하지_않으면_예외가_발생한다(String invalid) {
        authService.generateAccessAndRefreshToken(oAuthUser);
        AuthCommand.RenewalAccessToken request = new AuthCommand.RenewalAccessToken(invalid);

        assertThatThrownBy(() -> authService.generateRenewalAccessToken(request))
                .isInstanceOf(InvalidTokenException.class);
    }
}
