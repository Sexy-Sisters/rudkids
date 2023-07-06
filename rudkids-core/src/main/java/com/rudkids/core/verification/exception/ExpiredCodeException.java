package com.rudkids.core.verification.exception;

import com.rudkids.core.common.exception.UnauthorizedException;

public class ExpiredCodeException extends UnauthorizedException {
    private static final String MESSAGE = "인증코드가 만료되었습니다.";

    public ExpiredCodeException() {
        super(MESSAGE);
    }
}