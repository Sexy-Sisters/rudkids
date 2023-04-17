package com.rudkids.rudkids.domain.auth.exception;

import com.rudkids.rudkids.global.error.exception.UnauthorizedException;

public class ExpiredTokenException extends UnauthorizedException {
    private static final String MESSAGE = "만료된 토큰입니다.";

    public ExpiredTokenException() {
        super(MESSAGE);
    }
}
