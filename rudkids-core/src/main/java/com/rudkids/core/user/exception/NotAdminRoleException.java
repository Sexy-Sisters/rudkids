package com.rudkids.core.user.exception;

import com.rudkids.core.common.exception.ForbiddenException;

public class NotAdminRoleException extends ForbiddenException {
    private static final String MESSAGE = "관리자 권한이 아닙니다.";

    public NotAdminRoleException() {
        super(MESSAGE);
    }
}
