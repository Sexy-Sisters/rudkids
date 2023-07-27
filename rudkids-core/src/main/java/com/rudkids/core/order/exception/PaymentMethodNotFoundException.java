package com.rudkids.core.order.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class PaymentMethodNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 결제수단입니다.";

    public PaymentMethodNotFoundException() {
        super(MESSAGE);
    }
}
