package com.rudkids.rudkids.domain.order.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidOrderItemOptionPriceException extends BadRequestException {
    private static final String MESSAGE = "잘 못된 주문 상품 옵션 가격입니다.";

    public InvalidOrderItemOptionPriceException() {
        super(MESSAGE);
    }
}
