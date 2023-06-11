package com.rudkids.rudkids.domain.user.service;

import com.rudkids.rudkids.common.fixtures.user.UserServiceFixtures;
import com.rudkids.rudkids.domain.delivery.domain.Address;
import com.rudkids.rudkids.domain.delivery.domain.Delivery;
import com.rudkids.rudkids.domain.user.UserCommand;
import com.rudkids.rudkids.domain.user.UserInfo;
import com.rudkids.rudkids.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UserServiceTest extends UserServiceFixtures  {

    @DisplayName("[유저-수정]")
    @Test
    void 유저_정보를_수정한다() {
        //given
        UserCommand.Update command = UserCommand.Update.builder()
            .name("새로운 이름")
            .phoneNumber("01012345678")
            .profileImagePath("path")
            .profileImageUrl("url")
            .build();

        //when
        userService.update(user.getId(), command);

        //then
        User actual = userReader.getUser(user.getId());
        assertAll(() -> {
            assertThat(actual.getName()).isEqualTo("새로운 이름");
            assertThat(actual.getPhoneNumber()).isEqualTo("01012345678");
            assertThat(actual.getProfileImageUrl()).isEqualTo("url");
        });
    }

    @DisplayName("[유저-조회]")
    @Test
    void 유저_정보를_조회한다() {
        //given, when
        UserInfo.Main actual = userService.find(user.getId());

        //then
        assertAll(() -> {
            assertThat(actual.email()).isEqualTo("namse@gmail.com");
            assertThat(actual.name()).isEqualTo("남세원");
            assertThat(actual.gender()).isEqualTo("MALE");
            assertThat(actual.age()).isEqualTo(19);
            assertThat(actual.phoneNumber()).isEqualTo("01029401509");
            assertThat(actual.profileImage()).isEqualTo("url");
        });
    }

    @DisplayName("[유저-주소정보_조회]")
    @Test
    void 유저_주소정보들를_조회한다() {
        //given, when
        Delivery delivery1 = Delivery.builder()
            .user(user)
            .receiverName("receiverName")
            .receiverPhone("01029401509")
            .address(Address.create("address1", "address2", "zipCode"))
            .message("message")
            .build();
        Delivery delivery2 = Delivery.builder()
            .user(user)
            .receiverName("receiverName")
            .receiverPhone("01029401509")
            .address(Address.create("address1", "address2", "zipCode"))
            .message("message")
            .build();
        user.addDeliveryAddress(delivery1);
        user.addDeliveryAddress(delivery2);

        List<UserInfo.Addresses> actual = userService.findAddresses(user.getId());

        //then
        assertThat(actual).hasSize(2);
    }
}
