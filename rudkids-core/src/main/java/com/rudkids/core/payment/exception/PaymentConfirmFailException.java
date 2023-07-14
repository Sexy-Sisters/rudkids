package com.rudkids.core.payment.exception;

import com.rudkids.core.common.exception.InternalException;

public class PaymentConfirmFailException extends InternalException {
    private static final String MESSAGE = "결제 승인이 실패했습니다.";

    public PaymentConfirmFailException() {
        super(MESSAGE);
    }
}
