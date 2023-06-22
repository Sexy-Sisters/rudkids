package com.rudkids.core.user.domain;

import com.rudkids.core.user.exception.RoleTypeNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum RoleType {
    USER("일반 사용자"),
    PARTNER("가게 사장"),
    ADMIN("관리자");

    private final String description;

    public static RoleType toEnum(String description) {
        return Arrays.stream(values())
            .filter(it -> it.isSameDescription(description))
            .findFirst()
            .orElseThrow(RoleTypeNotFoundException::new);
    }

    private boolean isSameDescription(String description) {
        return this.name().equals(description);
    }
}
