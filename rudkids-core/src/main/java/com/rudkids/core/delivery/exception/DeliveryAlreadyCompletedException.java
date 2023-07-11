package com.rudkids.core.delivery.exception;

import com.rudkids.core.common.exception.ConflictException;

public class DeliveryAlreadyCompletedException extends ConflictException {
    private static final String MESSAGE = "이미 배송완료된 상품은 취소가 불가능합니다.";

    public DeliveryAlreadyCompletedException() {
        super(MESSAGE);
    }
}
