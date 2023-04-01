package com.rudkids.rudkids.domain.item.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidItemQuantityException extends BadRequestException {
    private static final String MESSAGE = "잘못된 수량입니다.";

    public InvalidItemQuantityException() {
        super(MESSAGE);
    }
}
