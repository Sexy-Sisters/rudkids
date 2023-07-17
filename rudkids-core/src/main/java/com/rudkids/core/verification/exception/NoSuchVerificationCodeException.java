package com.rudkids.core.verification.exception;

import com.rudkids.core.common.exception.ConflictException;

public class NoSuchVerificationCodeException extends ConflictException {
    private static final String MESSAGE = "회원의 검증코드가 아닙니다.";

    public NoSuchVerificationCodeException() {
        super(MESSAGE);
    }
}