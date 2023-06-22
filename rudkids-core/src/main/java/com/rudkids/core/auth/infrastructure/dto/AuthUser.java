package com.rudkids.core.auth.infrastructure.dto;

import com.rudkids.core.user.domain.*;
import lombok.Builder;

import java.util.UUID;

public class AuthUser {

    public record Login(UUID id) {}

    @Builder
    public record OAuth(
        String email,
        String name,
        String gender,
        int age,
        String phoneNumber,
        String profileImage,
        SocialType socialType
    ) {
        public User toEntity() {
            UserName initName = UserName.create(name);
            PhoneNumber initPhoneNumber = PhoneNumber.createDefault(phoneNumber);
            ProfileImage initProfileImage = ProfileImage.create("", profileImage);

            return User.builder()
                .email(email)
                .name(initName)
                .gender(gender)
                .age(age)
                .phoneNumber(initPhoneNumber)
                .profileImage(initProfileImage)
                .socialType(socialType)
                .build();
        }
    }
}
