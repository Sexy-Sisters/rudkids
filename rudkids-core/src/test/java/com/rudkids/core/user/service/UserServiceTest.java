package com.rudkids.core.user.service;

import com.rudkids.core.common.fixtures.UserServiceFixtures;
import com.rudkids.core.delivery.domain.Address;
import com.rudkids.core.delivery.domain.Delivery;
import com.rudkids.core.user.domain.PhoneNumber;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.dto.UserRequest;
import com.rudkids.core.user.dto.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UserServiceTest extends UserServiceFixtures {

    @DisplayName("[유저-수정]")
    @Test
    void 유저_정보를_수정한다() {
        //given
        UserRequest.Update command = UserRequest.Update.builder()
            .name("새로운 이름")
            .profileImagePath("path")
            .profileImageUrl("url")
            .build();

        //when
        userService.update(user.getId(), command);

        //then
        User actual = userRepository.getUser(user.getId());
        assertAll(() -> {
            assertThat(actual.getName()).isEqualTo("새로운 이름");
            assertThat(actual.getProfileImageUrl()).isEqualTo("url");
        });
    }

    @DisplayName("[유저-조회]")
    @Test
    void 유저_정보를_조회한다() {
        //given, when
        user.updatePhoneNumber(PhoneNumber.create("01023456789"));
        UserResponse.Info actual = userService.get(user.getId());

        //then
        assertAll(() -> {
            assertThat(actual.email()).isEqualTo("namse@gmail.com");
            assertThat(actual.name()).isEqualTo("남세원");
            assertThat(actual.phoneNumber()).isEqualTo("01023456789");
            assertThat(actual.profileImage()).isEqualTo("url");
        });
    }

    @DisplayName("[유저-주소정보_조회]")
    @Test
    void 유저_주소정보들를_조회한다() {
        //given, when
        Delivery delivery1 = Delivery.builder()
            .receiverName("receiverName")
            .receiverPhone("01029401509")
            .address(Address.create("address1", "address2", "zipCode"))
            .message("message")
            .build();
        delivery1.registerUser(user);
        Delivery delivery2 = Delivery.builder()
            .receiverName("receiverName")
            .receiverPhone("01029401509")
            .address(Address.create("address1", "address2", "zipCode"))
            .message("message")
            .build();
        delivery2.registerUser(user);

        List<UserResponse.Address> actual = userService.getAddresses(user.getId());

        //then
        assertThat(actual).hasSize(2);
    }
}
