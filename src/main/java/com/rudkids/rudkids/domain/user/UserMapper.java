package com.rudkids.rudkids.domain.user;

import com.rudkids.rudkids.domain.delivery.domain.Delivery;
import com.rudkids.rudkids.domain.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserInfo.Main toInfo(User user) {
        return UserInfo.Main.builder()
            .email(user.getEmail())
            .name(user.getName())
            .gender(user.getGender())
            .age(user.getAge())
            .phoneNumber(user.getPhoneNumber())
            .profileImage(user.getProfileImageUrl())
            .build();
    }

    public UserInfo.Addresses toInfo(Delivery delivery) {
        return UserInfo.Addresses.builder()
            .receiverName(delivery.getReceiverName())
            .receiverPhone(delivery.getReceiverPhone())
            .receiverAddress1(delivery.getAddress1())
            .receiverAddress2(delivery.getAddress2())
            .receiverZipCode(delivery.getZipCode())
            .message(delivery.getMessage())
            .build();
    }
}
