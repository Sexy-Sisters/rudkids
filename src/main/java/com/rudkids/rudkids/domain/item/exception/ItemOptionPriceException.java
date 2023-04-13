package com.rudkids.rudkids.domain.item.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class ItemOptionPriceException extends BadRequestException {
    private static final String MESSAGE = "잘못된 아이템 옵션 가격입니다.";

    public ItemOptionPriceException() {
        super(MESSAGE);
    }
}
