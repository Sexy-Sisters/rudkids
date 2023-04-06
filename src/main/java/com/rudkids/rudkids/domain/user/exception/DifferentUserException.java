package com.rudkids.rudkids.domain.user.exception;

import com.rudkids.rudkids.global.error.exception.ForbiddenException;

public class DifferentUserException extends ForbiddenException {
    private static final String MESSAGE = "같은 유저가 아닙니다.";

    public DifferentUserException() {
        super(MESSAGE);
    }
}
