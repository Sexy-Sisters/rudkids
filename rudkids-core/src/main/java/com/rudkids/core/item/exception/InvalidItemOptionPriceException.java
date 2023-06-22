package com.rudkids.core.item.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidItemOptionPriceException extends BadRequestException {
    private static final String MESSAGE = "잘못된 아이템 옵션 가격입니다.";

    public InvalidItemOptionPriceException() {
        super(MESSAGE);
    }
}
