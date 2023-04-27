package com.rudkids.rudkids.domain.auth.service;

import com.rudkids.rudkids.common.fixtures.auth.AuthServiceFixtures;
import com.rudkids.rudkids.domain.auth.AuthCommand;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.interfaces.auth.dto.AuthResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertAll;

class AuthServiceTest extends AuthServiceFixtures {

    @DisplayName("[Auth-토큰생성]")
    @Test
    void 토큰_생성을_하면_OAuth에서_인증_후_토큰을_반환한다() {
        AuthResponse.AccessAndRefreshToken actual = authService.generateAccessAndRefreshToken(oAuthUser);

        assertAll(() -> {
            assertThat(actual.accessToken()).isNotEmpty();
            assertThat(actual.refreshToken()).isNotEmpty();
            assertThat(userRepository.findAll()).hasSize(1);
        });
    }

    @DisplayName("[Auth-사용자생성]")
    @Test
    void 이미_가입된_회원이라면_추가로_회원이_생성되지_않는다() {
        authService.generateAccessAndRefreshToken(oAuthUser);

        authService.generateAccessAndRefreshToken(oAuthUser);
        List<User> findUsers = userRepository.findAll();

        assertThat(findUsers).hasSize(1);
    }

    @DisplayName("[Auth-리프래쉬토큰반환]")
    @Test
    void 이미_가입된_회원이_로그인_하면_저장된_리프래쉬_토큰을_반환한다() {
        AuthResponse.AccessAndRefreshToken actual = authService.generateAccessAndRefreshToken(oAuthUser);

        AuthResponse.AccessAndRefreshToken expect = authService.generateAccessAndRefreshToken(oAuthUser);

        assertThat(expect.refreshToken()).isEqualTo(actual.refreshToken());
    }

    @DisplayName("[Auth-새로운엑세스토큰발급]")
    @Test
    void 리프래쉬_토큰으로_새로운_엑세스_토큰을_발급한다() {
        AuthResponse.AccessAndRefreshToken response = authService.generateAccessAndRefreshToken(oAuthUser);
        AuthCommand.RenewalAccessToken request = new AuthCommand.RenewalAccessToken(response.refreshToken());

        AuthResponse.AccessToken renewalAccessToken = authService.generateRenewalAccessToken(request);

        assertThat(renewalAccessToken.accessToken()).isNotEmpty();
    }
}