package com.rudkids.rudkids.domain.auth.application;

import com.rudkids.rudkids.common.fixtures.auth.AuthServiceFixtures;
import com.rudkids.rudkids.domain.auth.exception.InvalidTokenException;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class AuthServiceImplTest extends AuthServiceFixtures {

    @DisplayName("토큰 생성을 하면 OAuth에서 인증 후 토큰을 반환한다.")
    @Test
    void 토큰_생성을_하면_OAuth에서_인증_후_토큰을_반환한다() {
        AuthResponse.AccessAndRefreshToken actual = authService.generateAccessAndRefreshToken(oAuthUser);

        assertAll(() -> {
            assertThat(actual.accessToken()).isNotEmpty();
            assertThat(actual.refreshToken()).isNotEmpty();
            assertThat(userRepository.findAll()).hasSize(1);
        });
    }

    @DisplayName("이미 가입된 회원이라면 추가로 회원이 생성되지 않는다.")
    @Test
    void 이미_가입된_회원이라면_추가로_회원이_생성되지_않는다() {
        authService.generateAccessAndRefreshToken(oAuthUser);

        authService.generateAccessAndRefreshToken(oAuthUser);
        List<User> findUsers = userRepository.findAll();

        assertThat(findUsers).hasSize(1);
    }

    @DisplayName("이미 가입된 회원이 로그인 하면 저장된 리프래쉬 토큰을 반환한다.")
    @Test
    void 이미_가입된_회원이_로그인_하면_저장된_리프래쉬_토큰을_반환한다() {
        AuthResponse.AccessAndRefreshToken actual = authService.generateAccessAndRefreshToken(oAuthUser);

        AuthResponse.AccessAndRefreshToken expect = authService.generateAccessAndRefreshToken(oAuthUser);

        assertThat(expect.refreshToken()).isEqualTo(actual.refreshToken());
    }

    @DisplayName("리프래쉬 토큰으로 새로운 엑세스 토큰을 발급한다.")
    @Test
    void 리프래쉬_토큰으로_새로운_엑세스_토큰을_발급한다() {
        AuthResponse.AccessAndRefreshToken response = authService.generateAccessAndRefreshToken(oAuthUser);
        AuthCommand.RenewalAccessToken request = new AuthCommand.RenewalAccessToken(response.refreshToken());

        AuthResponse.AccessToken renewalAccessToken = authService.generateRenewalAccessToken(request);

        assertThat(renewalAccessToken.accessToken()).isNotEmpty();
    }

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