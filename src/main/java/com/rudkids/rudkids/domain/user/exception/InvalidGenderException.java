package com.rudkids.rudkids.domain.user.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidGenderException extends BadRequestException {
    private static final String MESSAGE = "남자, 여자 중 하나를 선택해주세요.";

    public InvalidGenderException() {
        super(MESSAGE);
    }
}
