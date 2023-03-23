package com.rudkids.rudkids.domain.auth.exception;

public class NoSuchTokenException extends RuntimeException {
    private static final String MESSAGE = "회원의 리프레쉬 토큰이 아닙니다.";

    public NoSuchTokenException() {
        super(MESSAGE);
    }
}
