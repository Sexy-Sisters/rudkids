package com.rudkids.rudkids.domain.user.domain;

public enum RoleType {
    USER("일반 사용자"),
    PARTNER("가게 사장"),
    ADMIN("관리자");

    RoleType(String description) {
        this.description = description;
    }

    private final String description;

    public String getDescription() {
        return description;
    }
}
