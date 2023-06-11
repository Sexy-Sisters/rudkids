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

    @Builder
    public record Addresses(
        String receiverName,
        String receiverPhone,
        String receiverAddress1,
        String receiverAddress2,
        String receiverZipCode,
        String message
    ) {
    }
}
