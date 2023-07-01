package com.rudkids.core.verification.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class VerificationCodeNotFoundException extends NotFoundException {
    private static final String MESSAGE = "인증코드가 존재하지 않습니다.";

    public VerificationCodeNotFoundException() {
        super(MESSAGE);
    }
}