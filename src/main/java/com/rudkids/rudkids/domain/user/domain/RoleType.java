package com.rudkids.rudkids.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    USER("일반 사용자"), ADMIN("관리자");
    private final String description;
}
