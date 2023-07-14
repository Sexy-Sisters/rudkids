package com.rudkids.core.delivery.domain;

import com.rudkids.core.user.domain.ProfileImage;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.domain.UserName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryTest {

    @Nested
    @DisplayName("기본배송지로 변경한다")
    class changeBasic {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            var address = Address.create("부산광역시", "서구", "124332");
            var delivery = Delivery.builder()
                .receiverName("수신자")
                .receiverPhone("01012345678")
                .address(address)
                .message("메세지")
                .isBasic(false)
                .build();

            ProfileImage profileImage = ProfileImage.create("path", "url");
            var user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(profileImage)
                .build();

            delivery.registerUser(user);

            //when
            delivery.changeBasic();

            //then
            assertThat(delivery.isBasic()).isTrue();
        }

        @Test
        @DisplayName("성공: 기본배송지로 변경하면 기존의 기본배송지는 false가 된다")
        void success2() {
            //given
            User user = User.builder()
                .email("namse@gmail.com")
                .name(UserName.create("남세"))
                .profileImage(ProfileImage.create("path", "url"))
                .build();

            var address = Address.create("부산광역시", "서구", "124332");
            var delivery = Delivery.builder()
                .receiverName("수신자")
                .receiverPhone("01012345678")
                .address(address)
                .message("메세지")
                .isBasic(true)
                .build();
            delivery.registerUser(user);

            //when
            var newDelivery = Delivery.builder()
                .receiverName("수신자")
                .receiverPhone("01012345678")
                .address(address)
                .message("메세지")
                .isBasic(true)
                .build();
            newDelivery.registerUser(user);
            newDelivery.checkChangeBasicDelivery();

            //then
            assertThat(delivery.isBasic()).isFalse();
        }
    }
}
