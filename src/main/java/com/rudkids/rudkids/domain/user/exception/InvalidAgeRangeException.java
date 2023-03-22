package com.rudkids.rudkids.domain.user.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidAgeRangeException extends BadRequestException {
    private static final String MESSAGE = "1부터 100내에서 선택하세요.";

    public InvalidAgeRangeException() {
        super(MESSAGE);
    }
}
