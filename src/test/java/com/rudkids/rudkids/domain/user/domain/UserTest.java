package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import com.rudkids.rudkids.domain.user.exception.InvalidAgeRangeException;
import com.rudkids.rudkids.domain.user.exception.InvalidGenderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("관리자 권한일 경우 통과한다.")
    @Test
    void 관리자_권한일_경우_통과한다() {
        //given
        User user = User.builder()
                .email("namsewon@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.MAIL)
                .socialType(SocialType.GOOGLE)
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
                .email("namsewon@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.MAIL)
                .socialType(SocialType.GOOGLE)
                .build();

        //then
        assertThatThrownBy(user::validateAdminRole)
                .isInstanceOf(NotAdminRoleException.class);
    }

    @DisplayName("나이와 성별을 수정한다.")
    @Test
    void 나이와_성별을_수정한다() {
        //given
        User user = User.builder()
                .email("namsewon@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.MAIL)
                .socialType(SocialType.GOOGLE)
                .build();

        //when
        Age age = Age.create(7);
        String gender = "FEMALE";
        user.update(age, gender);

        //then
        assertAll(() -> {
            assertThat(user.getAge()).isEqualTo(7);
            assertThat(user.getGender()).isEqualTo(Gender.FEMALE);
        });
    }

    @DisplayName("잘못된 나이로 수정할 경우 예외가 발생한다.")
    @Test
    void 잘못된_나이로_수정할_경우_예외가_발생한다() {
        //given
        User user = User.builder()
                .email("namsewon@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.MAIL)
                .socialType(SocialType.GOOGLE)
                .build();

        //when
        int invalidAge = 108;
        String gender = "FEMALE";

        //then
        assertThatThrownBy(() -> user.update(Age.create(invalidAge), gender))
                .isInstanceOf(InvalidAgeRangeException.class);
    }

    @DisplayName("잘못된 성별을 수정할 경우 예외가 발생한다.")
    @Test
    void 잘못된_성별을_수정할_경우_예외가_발생한다() {
        //given
        User user = User.builder()
                .email("namsewon@gmail.com")
                .name("남세")
                .age(Age.create(18))
                .gender(Gender.MAIL)
                .socialType(SocialType.GOOGLE)
                .build();

        //when
        Age age = Age.create(7);
        String invalidGender = "Female";

        //then
        assertThatThrownBy(() -> user.update(age, invalidGender))
                .isInstanceOf(InvalidGenderException.class);
    }
}