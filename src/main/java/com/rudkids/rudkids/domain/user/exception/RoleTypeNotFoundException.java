package com.rudkids.rudkids.domain.user.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class RoleTypeNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 권한입니다.";

    public RoleTypeNotFoundException() {
        super(MESSAGE);
    }
}
