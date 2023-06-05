package com.rudkids.rudkids.domain.user;

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
}
