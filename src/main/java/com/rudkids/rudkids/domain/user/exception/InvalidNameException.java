package com.rudkids.rudkids.domain.user.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidNameException extends BadRequestException {
    private static final String MESSAGE = "잘못된 이름입니다.";

    public InvalidNameException() {
        super(MESSAGE);
    }
}
