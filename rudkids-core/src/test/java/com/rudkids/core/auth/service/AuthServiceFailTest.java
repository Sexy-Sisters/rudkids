package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthRequest;
import com.rudkids.core.auth.exception.InvalidTokenException;
import com.rudkids.core.common.fixtures.AuthServiceFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AuthServiceFailTest extends AuthServiceFixtures {

    @DisplayName("[Auth-토큰생성-InvalidTokenException]")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "invalid"})
    void 리프래쉬_토큰으로_새로운_엑세스_토큰을_발급할_때_존재하지_않으면_예외가_발생한다(String invalid) {
        authService.generateAccessAndRefreshToken(oAuthUser);
        AuthRequest.RenewalToken request = new AuthRequest.RenewalToken(invalid);

        assertThatThrownBy(() -> authService.generateRenewalAccessToken(request))
                .isInstanceOf(InvalidTokenException.class);
    }
}