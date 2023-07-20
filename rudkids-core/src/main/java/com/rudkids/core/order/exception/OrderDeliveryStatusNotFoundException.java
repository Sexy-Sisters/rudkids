package com.rudkids.core.order.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class OrderDeliveryStatusNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 배송상태입니다.";

    public OrderDeliveryStatusNotFoundException() {
        super(MESSAGE);
    }
}
