package com.rudkids.core.user.dto;

import lombok.Builder;

public class UserRequest {

    @Builder
    public record Update(
        String name,
        String profileImagePath,
        String profileImageUrl
    ) {}
}
