package com.rudkids.rudkids.domain.user.exception;

import com.rudkids.rudkids.global.error.exception.ForbiddenException;

public class NotAdminRoleException extends ForbiddenException {
    private static final String MESSAGE = "관리자 권한이 아닙니다.";

    public NotAdminRoleException() {
        super(MESSAGE);
    }
}
