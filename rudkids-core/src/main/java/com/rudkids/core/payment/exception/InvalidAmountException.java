package com.rudkids.core.payment.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidAmountException extends BadRequestException {
    private static final String MESSAGE = "잘못된 결재금액입니다.";

    public InvalidAmountException() {
        super(MESSAGE);
    }
}
