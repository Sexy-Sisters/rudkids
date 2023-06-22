package com.rudkids.core.order.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class OrderNotFoundException extends NotFoundException {
    private static final String MESSAGE = "주문내역을 찾을 수 없습니다.";

    public OrderNotFoundException() {
        super(MESSAGE);
    }
}
