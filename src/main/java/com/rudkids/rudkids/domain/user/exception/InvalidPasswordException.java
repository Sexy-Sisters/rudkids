package com.rudkids.rudkids.domain.user.exception;

public class InvalidPasswordException extends RuntimeException {
    private static final String MESSAGE = "잘못된 패스워드 입니다.";

    public InvalidPasswordException() {
        super(MESSAGE);
    }
}
