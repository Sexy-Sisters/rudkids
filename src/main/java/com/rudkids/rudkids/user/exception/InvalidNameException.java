package com.rudkids.rudkids.user.exception;

public class InvalidNameException extends RuntimeException {
    private static final String MESSAGE = "잘못된 이름입니다.";

    public InvalidNameException() {
        super(MESSAGE);
    }
}
