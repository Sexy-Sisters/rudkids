package com.rudkids.core.auth.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class NoSuchTokenException extends NotFoundException {
    private static final String MESSAGE = "회원의 리프레쉬 토큰이 아닙니다.";

    public NoSuchTokenException() {
        super(MESSAGE);
    }
}
