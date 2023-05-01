package com.rudkids.rudkids.domain.order.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class OrderStatusNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 주문 상태입니다.";

    public OrderStatusNotFoundException() {
        super(MESSAGE);
    }
}
