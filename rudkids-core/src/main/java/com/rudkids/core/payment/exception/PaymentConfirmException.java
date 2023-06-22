package com.rudkids.core.payment.exception;

import com.rudkids.core.common.exception.InternalException;

public class PaymentConfirmException extends InternalException {
    private static final String MESSAGE = "결제승인이 오류가 났습니다.";

    public PaymentConfirmException() {
        super(MESSAGE);
    }
}
