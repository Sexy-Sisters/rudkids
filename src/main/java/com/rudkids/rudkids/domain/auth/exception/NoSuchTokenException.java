package com.rudkids.rudkids.domain.auth.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class NoSuchTokenException extends NotFoundException {
    private static final String MESSAGE = "회원의 리프레쉬 토큰이 아닙니다.";

    public NoSuchTokenException() {
        super(MESSAGE);
    }
}
