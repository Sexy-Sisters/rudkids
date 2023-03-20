package com.rudkids.rudkids.domain.auth.exception;

public class InvalidTokenException extends RuntimeException {
    private static final String MESSAGE = "토큰 형식이 잘못되었습니다.";

    public InvalidTokenException() {
        super(MESSAGE);
    }
}
