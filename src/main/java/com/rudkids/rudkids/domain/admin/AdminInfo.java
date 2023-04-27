package com.rudkids.rudkids.domain.admin;

import lombok.Builder;

public class AdminInfo {

    @Builder
    public record User(
        String email,
        String name,
        int age,
        String gender,
        String phoneNumber,
        String roleType
    ) {
    }
}
