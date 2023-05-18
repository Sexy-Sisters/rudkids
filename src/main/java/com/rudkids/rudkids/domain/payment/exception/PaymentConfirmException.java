package com.rudkids.rudkids.domain.payment.exception;

import com.rudkids.rudkids.global.error.exception.InternalException;

public class PaymentConfirmException extends InternalException {
    private static final String MESSAGE = "결제승인이 오류가 났습니다.";

    public PaymentConfirmException() {
        super(MESSAGE);
    }
}
