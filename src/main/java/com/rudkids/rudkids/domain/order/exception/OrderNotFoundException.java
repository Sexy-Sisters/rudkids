package com.rudkids.rudkids.domain.order.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class OrderNotFoundException extends NotFoundException {
    private static final String MESSAGE = "주문내역을 찾을 수 없습니다.";

    public OrderNotFoundException() {
        super(MESSAGE);
    }
}
