package com.rudkids.rudkids.domain.user.exception;

public class InvalidPhoneNumberFormatException extends RuntimeException {
    private static final String MESSAGE = "잘못된 전화번호 양식입니다.";

    public InvalidPhoneNumberFormatException() {
        super(MESSAGE);
    }
}
