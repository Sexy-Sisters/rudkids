package com.rudkids.core.item.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidItemQuantityException extends BadRequestException {
    private static final String MESSAGE = "잘못된 수량입니다.";

    public InvalidItemQuantityException() {
        super(MESSAGE);
    }
}
