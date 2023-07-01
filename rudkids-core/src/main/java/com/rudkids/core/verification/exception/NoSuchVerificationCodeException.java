package com.rudkids.core.verification.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class NoSuchVerificationCodeException extends NotFoundException {
    private static final String MESSAGE = "회원의 검증코드가 아닙니다.";

    public NoSuchVerificationCodeException() {
        super(MESSAGE);
    }
}