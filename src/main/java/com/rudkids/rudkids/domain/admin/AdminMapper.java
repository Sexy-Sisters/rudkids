package com.rudkids.rudkids.domain.admin;

import com.rudkids.rudkids.domain.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminInfo.User toInfo(User user) {
        return AdminInfo.User.builder()
            .email(user.getEmail())
            .name(user.getName())
            .age(user.getAge())
            .gender(user.getGender())
            .phoneNumber(user.getPhoneNumber())
            .roleType(user.getRoleType().name())
            .build();
    }
}
