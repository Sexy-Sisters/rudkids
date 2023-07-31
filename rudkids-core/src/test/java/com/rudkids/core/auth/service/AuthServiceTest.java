package com.rudkids.core.auth.service;

import com.rudkids.core.auth.dto.AuthRequest;
import com.rudkids.core.auth.dto.AuthResponse;
import com.rudkids.core.auth.exception.InvalidTokenException;
import com.rudkids.core.common.fixtures.auth.AuthServiceFixtures;
import com.rudkids.core.user.domain.PhoneNumber;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.exception.InvalidPhoneNumberException;
import com.rudkids.core.user.exception.NotFoundUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class AuthServiceTest extends AuthServiceFixtures {

    @Nested
    @DisplayName("토큰을 발급하고 반환한다")
    class generateAccessAndRefreshToken {

        @Test
        @DisplayName("성공")
        void success() {
            //given

            //when
            AuthResponse.AccessAndRefreshToken actual = authService.generateAccessAndRefreshToken(oAuthUser);

            //then
            assertAll(() -> {
                assertThat(actual.accessToken()).isNotEmpty();
                assertThat(actual.refreshToken()).isNotEmpty();
            });
        }

        @Test
        @DisplayName("성공: 이미 가입된 회원이 한번 더 로그인을 하면 추가로 회원이 생성되지 않는다")
        void success2() {
            //given
            authService.generateAccessAndRefreshToken(oAuthUser);

            //when
            authService.generateAccessAndRefreshToken(oAuthUser);

            //then
            List<User> findUsers = userRepository.findAll();
            assertThat(findUsers).hasSize(1);
        }

        @DisplayName("성공: 이미 가입된 회원이 한번 더 로그인을 하면 저장된 리프래쉬 토큰을 가져온다")
        @Test
        void success3() {
            //given
            AuthResponse.AccessAndRefreshToken actual = authService.generateAccessAndRefreshToken(oAuthUser);

            //when
            AuthResponse.AccessAndRefreshToken expect = authService.generateAccessAndRefreshToken(oAuthUser);

            //then
            assertThat(expect.refreshToken()).isEqualTo(actual.refreshToken());
        }
    }

    @Nested
    @DisplayName("리프래쉬 토큰으로 새로운 엑세스 토큰을 발급한다")
    class generateRenewalAccessToken {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            AuthResponse.AccessAndRefreshToken response = authService.generateAccessAndRefreshToken(oAuthUser);

            //when
            AuthRequest.RenewalToken request = new AuthRequest.RenewalToken(response.refreshToken());
            AuthResponse.AccessToken renewalAccessToken = authService.generateRenewalAccessToken(request);

            //then
            assertThat(renewalAccessToken.accessToken()).isNotEmpty();
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "invalid"})
        @DisplayName("실패: 리프래쉬 토큰값이 비어있거나 잘못된 값")
        void fail(String invalid) {
            //given
            authService.generateAccessAndRefreshToken(oAuthUser);

            //when, then
            AuthRequest.RenewalToken request = new AuthRequest.RenewalToken(invalid);

            assertThatThrownBy(() -> authService.generateRenewalAccessToken(request))
                .isInstanceOf(InvalidTokenException.class);
        }
    }

    @Nested
    @DisplayName("인증된 회원번호를 저장한다")
    class saveAuthenticatedPhoneNumber {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            var user = User.builder()
                .email("email")
                .name(UserName.create("name"))
                .phoneNumber(PhoneNumber.createDefault())
                .profileImage(ProfileImage.create("path", "url"))
                .build();

            userRepository.save(user);

            //when
            final String phoneNumber = "01012345678";
            userService.updatePhoneNumber(user.getId(), phoneNumber);

            //then
            assertThat(user.getPhoneNumber()).isEqualTo("01012345678");
        }

        @Test
        @DisplayName("실패: 존재하지 않는 유저 id")
        void fail() {
            //given
            final UUID invalidUserId = UUID.randomUUID();
            final String phoneNumber = "01012345678";

            //when, then
            assertThatThrownBy(() -> userService.updatePhoneNumber(invalidUserId, phoneNumber))
                .isInstanceOf(NotFoundUserException.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "invalid"})
        @DisplayName("실패: 잘못된 형식의 전화번호")
        void fail2(String invalid) {
            //given
            var user = User.builder()
                .email("email")
                .name(UserName.create("name"))
                .phoneNumber(PhoneNumber.createDefault())
                .profileImage(ProfileImage.create("path", "url"))
                .build();

            userRepository.save(user);

            //when, then
            assertThatThrownBy(() -> userService.updatePhoneNumber(user.getId(), invalid))
                .isInstanceOf(InvalidPhoneNumberException.class);
        }
    }
}