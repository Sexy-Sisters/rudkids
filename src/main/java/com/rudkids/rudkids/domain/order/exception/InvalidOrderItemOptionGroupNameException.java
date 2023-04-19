package com.rudkids.rudkids.domain.order.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidOrderItemOptionGroupNameException extends BadRequestException {
    private static final String MESSAGE = "잘 못된 주문 상품의 옵션그룹 이름입니다.";

    public InvalidOrderItemOptionGroupNameException() {
        super(MESSAGE);
    }
}
