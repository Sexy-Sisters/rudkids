package com.rudkids.rudkids.domain.auth.exception;

public class NotFoundTokenException extends RuntimeException {
    private static final String MESSAGE = "토큰이 존재하지 않습니다.";

    public NotFoundTokenException() {
        super(MESSAGE);
    }
}
