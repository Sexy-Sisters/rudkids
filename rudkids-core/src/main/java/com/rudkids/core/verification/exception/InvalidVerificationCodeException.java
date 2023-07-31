package com.rudkids.core.verification.exception;

import com.rudkids.core.common.exception.ConflictException;

public class InvalidVerificationCodeException extends ConflictException {
    private static final String MESSAGE = "잘못된 검증코드입니다.";

    public InvalidVerificationCodeException() {
        super(MESSAGE);
    }
}