package com.rudkids.core.order.exception;

import com.rudkids.core.common.exception.ConflictException;

public class OrderNotCancelException extends ConflictException {
    private static final String MESSAGE = "취소가 불가능합니다";

    public OrderNotCancelException() {
        super(MESSAGE);
    }
}
