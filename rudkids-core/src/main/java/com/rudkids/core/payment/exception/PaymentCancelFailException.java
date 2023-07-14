package com.rudkids.core.payment.exception;

import com.rudkids.core.common.exception.InternalException;

public class PaymentCancelFailException extends InternalException {
    private static final String MESSAGE = "결제취소가 실패했습니다.";

    public PaymentCancelFailException() {
        super(MESSAGE);
    }
}
