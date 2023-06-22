package com.rudkids.core.user.dto;

import lombok.Builder;

public class UserRequest {

    @Builder
    public record Update(
        String name,
        String phoneNumber,
        String profileImagePath,
        String profileImageUrl
    ) {}
}
