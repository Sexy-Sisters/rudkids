package com.rudkids.core.user.exception;

import com.rudkids.core.common.exception.ForbiddenException;

public class DifferentUserException extends ForbiddenException {
    private static final String MESSAGE = "같은 유저가 아닙니다.";

    public DifferentUserException() {
        super(MESSAGE);
    }
}
