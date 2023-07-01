package com.rudkids.core.user.domain;

import com.rudkids.core.user.exception.NotAdminRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @DisplayName("관리자 권한일 경우 통과한다.")
    @Test
    void 관리자_권한일_경우_통과한다() {
        //given
        User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .build();

        //when
        user.changeAuthorityAdmin();

        //then
        user.validateAdminRole();
    }

    @DisplayName("관리자 권한이 아닐경우 예외가 발생한다.")
    @Test
    void 관리자_권한이_아닐경우_예외가_발생한다() {
        //given, when
        User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .build();

        //then
        assertThatThrownBy(user::validateAdminRole)
                .isInstanceOf(NotAdminRoleException.class);
    }
}