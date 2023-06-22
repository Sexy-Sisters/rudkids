package com.rudkids.core.delivery.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class DeliveryNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 배송지입니다.";

    public DeliveryNotFoundException() {
        super(MESSAGE);
    }
}
