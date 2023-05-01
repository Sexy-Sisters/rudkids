package com.rudkids.rudkids.domain.user.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidPhoneNumberException extends BadRequestException {
    private static final String MESSAGE = "잘못된 전화번호입니다.";

    public InvalidPhoneNumberException() {
        super(MESSAGE);
    }
}
