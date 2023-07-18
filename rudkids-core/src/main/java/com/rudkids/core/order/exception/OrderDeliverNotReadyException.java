package com.rudkids.core.order.exception;

import com.rudkids.core.common.exception.ConflictException;

public class OrderDeliverNotReadyException extends ConflictException {
    private static final String MESSAGE = "배송준비중인 상품만 취소가 가능합니다.";

    public OrderDeliverNotReadyException() {
        super(MESSAGE);
    }
}
