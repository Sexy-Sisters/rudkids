package com.rudkids.rudkids.user.exception;

public class InvalidEmailFormatException extends RuntimeException {
    private static final String MESSAGE = "잘못된 이메일 형식입니다.";

    public InvalidEmailFormatException() {
        super(MESSAGE);
    }
}
