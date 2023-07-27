package com.rudkids.core.order.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class BankCodeNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 은행코드입니다.";

    public BankCodeNotFoundException() {
        super(MESSAGE);
    }
}
