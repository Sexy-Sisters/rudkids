package com.rudkids.rudkids.domain.auth;

import com.rudkids.rudkids.domain.user.domain.SocialType;
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
    }
}
