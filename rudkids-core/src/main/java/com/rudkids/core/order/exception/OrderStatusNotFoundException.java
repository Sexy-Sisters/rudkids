package com.rudkids.core.order.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class OrderStatusNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 주문 상태입니다.";

    public OrderStatusNotFoundException() {
        super(MESSAGE);
    }
}
