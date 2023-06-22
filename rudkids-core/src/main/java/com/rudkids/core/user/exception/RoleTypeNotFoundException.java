package com.rudkids.core.user.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class RoleTypeNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 권한입니다.";

    public RoleTypeNotFoundException() {
        super(MESSAGE);
    }
}
