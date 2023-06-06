package com.rudkids.rudkids.interfaces.user.dto;

public class UserRequest {

    public record Update(
        String name,
        String phoneNumber,
        String profileImagePath,
        String profileImageUrl
    ) {
    }
}
