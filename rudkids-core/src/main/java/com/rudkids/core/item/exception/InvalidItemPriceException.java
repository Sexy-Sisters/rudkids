package com.rudkids.core.item.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidItemPriceException extends BadRequestException {
    private static final String MESSAGE = "잘못된 가격입니다.";

    public InvalidItemPriceException() {
        super(MESSAGE);
    }
}
