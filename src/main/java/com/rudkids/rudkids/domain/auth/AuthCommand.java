package com.rudkids.rudkids.domain.auth;

import com.rudkids.rudkids.domain.user.domain.*;
import lombok.Builder;

public class AuthCommand {

    @Builder
    public record RenewalAccessToken(String refreshToken) {
    }

    @Builder
    public record OAuthUser(
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
