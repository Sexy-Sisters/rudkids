package com.rudkids.rudkids.domain.user.domain;

import lombok.Getter;

@Getter
public enum RoleType {
    USER("일반 사용자"),
    ADMIN("관리자");

    RoleType(String description) {
        this.description = description;
    }

    private final String description;
}
