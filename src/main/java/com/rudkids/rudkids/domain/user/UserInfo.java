package com.rudkids.rudkids.domain.user;

import lombok.Builder;

public class UserInfo {

    @Builder
    public record Main(
        String email,
        String name,
        String gender,
        int age,
        String phoneNumber,
        String profileImage
    ) {
    }
}
