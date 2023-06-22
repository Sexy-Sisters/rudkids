package com.rudkids.core.user.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidNameException extends BadRequestException {
    private static final String MESSAGE = "잘못된 이름입니다.";

    public InvalidNameException() {
        super(MESSAGE);
    }
}
