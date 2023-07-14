package com.rudkids.core.payment.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class PaymentNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 결제방법입니다.";

    public PaymentNotFoundException() {
        super(MESSAGE);
    }
}
