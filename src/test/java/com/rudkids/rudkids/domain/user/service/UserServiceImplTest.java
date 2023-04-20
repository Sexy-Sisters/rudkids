package com.rudkids.rudkids.domain.user.service;

import com.rudkids.rudkids.common.fixtures.user.UserServiceFixtures;
import com.rudkids.rudkids.domain.user.UserCommand;
import com.rudkids.rudkids.domain.user.domain.Gender;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.exception.InvalidAgeRangeException;
import com.rudkids.rudkids.domain.user.exception.InvalidGenderException;
import com.rudkids.rudkids.domain.user.exception.NotFoundUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceImplTest extends UserServiceFixtures {

    @DisplayName("올바른 요청을 보냈을 때 수정 완료")
    @Test
    void update() {
        UserCommand.Update request = new UserCommand.Update(유저_나이, 유저_성별);
        userService.update(user.getId(), request);

        User findUser = userRepository.findById(user.getId())
                        .orElseThrow(NotFoundUserException::new);

        assertAll(
                () -> assertEquals(findUser.getAge(), 18),
                () -> assertThat(findUser.getGender()).isEqualTo(Gender.MAIL)
        );
    }

    @DisplayName("올바르지 않는 나이를 입력하고 요청하면 예외 발생")
    @Test
    void create_InvalidAgeRangeException_when_update() {
        UserCommand.Update request = new UserCommand.Update(잘못된_유저_나이, 유저_성별);

        assertThatThrownBy(() -> userService.update(user.getId(), request))
                .isInstanceOf(InvalidAgeRangeException.class);
    }

    @DisplayName("올바르지 않는 성별을 입력하고 요청하면 예외 발생")
    @Test
    void create_InvalidGenderException_when_update() {
        UserCommand.Update request = new UserCommand.Update(유저_나이, 잘못된_유저_성별);

        assertThatThrownBy(() -> userService.update(user.getId(), request))
                .isInstanceOf(InvalidGenderException.class);
    }
}