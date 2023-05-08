package com.rudkids.rudkids.domain.order.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class OrderAlreadyPaidException extends BadRequestException {
    private static final String MESSAGE = "이미 결제된 주문입니다.";
    public OrderAlreadyPaidException() {
        super(MESSAGE);
    }
}
