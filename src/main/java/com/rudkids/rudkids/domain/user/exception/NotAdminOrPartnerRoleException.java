package com.rudkids.rudkids.domain.user.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;
import com.rudkids.rudkids.global.error.exception.ForbiddenException;

public class NotAdminOrPartnerRoleException extends ForbiddenException {
    private static final String MESSAGE = "관리자 혹은 파트너 권한이 아닙니다.";

    public NotAdminOrPartnerRoleException() {
        super(MESSAGE);
    }
}
