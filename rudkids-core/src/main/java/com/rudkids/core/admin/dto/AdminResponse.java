package com.rudkids.core.admin.dto;

import com.rudkids.core.user.domain.User;

public class AdminResponse {

    public record UserInfo(
        String email,
        String name,
        String phoneNumber,
        String profileImageUrl,
        String roleType
    ) {
        public UserInfo(User user) {
            this(
                user.getEmail(),
                user.getName(),
                user.getPhoneNumber(),
                user.getProfileImageUrl(),
                user.getRoleType().name()
            );
        }
    }
}
