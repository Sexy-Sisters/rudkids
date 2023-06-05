package com.rudkids.rudkids.domain.user;

import lombok.Builder;

public class UserCommand {
    @Builder
    public record SignUp(String phoneNumber) {
    }

    @Builder
    public record Update(
        String name,
        String phoneNumber,
        String profileImagePath,
        String profileImageUrl
    ) {
    }
}
