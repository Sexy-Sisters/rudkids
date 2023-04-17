package com.rudkids.rudkids.domain.user.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleType {
    USER("일반 사용자"),
    PARTNER("가게 사장"),
    ADMIN("관리자");

    private final String description;
}