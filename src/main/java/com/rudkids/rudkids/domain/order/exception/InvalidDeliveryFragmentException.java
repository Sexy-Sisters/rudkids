package com.rudkids.rudkids.domain.order.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidDeliveryFragmentException extends BadRequestException {
    private static final String MESSAGE = "잘 못된 배송정보입니다.";

    public InvalidDeliveryFragmentException() {
        super(MESSAGE);
    }
}
