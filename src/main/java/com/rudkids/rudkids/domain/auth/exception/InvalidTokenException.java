package com.rudkids.rudkids.domain.auth.exception;

import com.rudkids.rudkids.global.error.exception.UnauthorizedException;

public class InvalidTokenException extends UnauthorizedException {
    private static final String MESSAGE = "토큰 형식이 잘못되었습니다.";

    public InvalidTokenException() {
        super(MESSAGE);
    }
}
