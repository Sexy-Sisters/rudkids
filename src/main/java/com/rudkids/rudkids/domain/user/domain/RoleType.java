package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.RoleTypeNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum RoleType {
    USER("일반 사용자"),
    PARTNER("가게 사장"),
    ADMIN("관리자");

    private final String description;

    public static RoleType validate(RoleType role) {
        return Arrays.stream(values())
            .filter(roleType -> roleType.equals(role))
            .findFirst()
            .orElseThrow(RoleTypeNotFoundException::new);
    }
}