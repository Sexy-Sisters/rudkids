package com.rudkids.core.item.exception;

import com.rudkids.core.common.exception.ForbiddenException;

public class QuantityDepletedException extends ForbiddenException {
    private static final String MESSAGE = "재고가 부족합니다.";

    public QuantityDepletedException() {
        super(MESSAGE);
    }
}
