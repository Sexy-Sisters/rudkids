package com.rudkids.rudkids.domain.item.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidItemPriceException extends BadRequestException {
    private static final String MESSAGE = "잘못된 가격입니다.";

    public InvalidItemPriceException() {
        super(MESSAGE);
    }
}
