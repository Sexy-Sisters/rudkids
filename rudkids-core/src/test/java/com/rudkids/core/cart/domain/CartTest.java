package com.rudkids.core.cart.domain;

import com.rudkids.core.item.domain.*;
import com.rudkids.core.user.domain.PhoneNumber;
import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import com.rudkids.core.user.exception.DifferentUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CartTest {

    @Nested
    @DisplayName("같은 유저인지 검증한다")
    class validateHasSameUser {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            Cart cart = Cart.create(user);

            //when, then
            cart.validateHasSameUser(user);
        }

        @Test
        @DisplayName("실패: 다른유저")
        void fail() {
            //given
            User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();
            Cart cart = Cart.create(user);

            //when, then
            User differentUser = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("다른유저"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();

            assertThatThrownBy(() -> cart.validateHasSameUser(differentUser))
                .isInstanceOf(DifferentUserException.class);
        }
    }
}