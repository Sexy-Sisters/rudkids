package com.rudkids.rudkids.domain.user.exception;

public class InvalidUserNameException extends RuntimeException {
    private static final String MESSAGE = "잘못된 이름입니다.";

    public InvalidUserNameException() {
        super(MESSAGE);
    }
}
