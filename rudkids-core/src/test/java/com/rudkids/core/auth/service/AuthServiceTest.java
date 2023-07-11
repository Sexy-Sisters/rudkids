package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthRequest;
import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.dto.AuthUser;
import com.rudkids.core.common.fixtures.AuthServiceFixtures;
import com.rudkids.core.user.domain.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertAll;

class AuthServiceTest extends AuthServiceFixtures {

    @Test
    @DisplayName("토큰을 발급하고 반환한다")
    void success1() {
        //given

        //when
        AuthResponse.AccessAndRefreshToken actual = authService.generateAccessAndRefreshToken(oAuthUser);

        //then
        assertAll(() -> {
            assertThat(actual.accessToken()).isNotEmpty();
            assertThat(actual.refreshToken()).isNotEmpty();
        });
    }

    @DisplayName("이미 가입된 회원이라면 추가로 회원이 생성되지 않는다")
    @Test
    void success2() {
        //given
        authService.generateAccessAndRefreshToken(oAuthUser);

        //when
        authService.generateAccessAndRefreshToken(oAuthUser);

        //then
        List<User> findUsers = userRepository.findAll();
        assertThat(findUsers).hasSize(1);
    }

    @DisplayName("이미 가입된 회원이 한번 더 로그인을 하면 저장된 리프래쉬 토큰을 가져온다")
    @Test
    void success3() {
        //given
        AuthResponse.AccessAndRefreshToken actual = authService.generateAccessAndRefreshToken(oAuthUser);

        //when
        AuthResponse.AccessAndRefreshToken expect = authService.generateAccessAndRefreshToken(oAuthUser);

        //then
        assertThat(expect.refreshToken()).isEqualTo(actual.refreshToken());
    }

    @Disabled("보류")
    @DisplayName("새로운 엑세스 토큰을 발급한다")
    @Test
    void success4() {
        //given
        AuthResponse.AccessAndRefreshToken response = authService.generateAccessAndRefreshToken(oAuthUser);

        //when
        AuthRequest.RenewalToken request = new AuthRequest.RenewalToken(response.refreshToken());
        AuthResponse.AccessToken renewalAccessToken = authService.generateRenewalAccessToken(request);

        //then
        assertThat(renewalAccessToken.accessToken()).isNotEqualTo(response.accessToken());
    }
}