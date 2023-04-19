package com.rudkids.rudkids.domain.order.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class NotFoundOrderException extends BadRequestException {
    private static final String MESSAGE = "주문내역을 찾을 수 없습니다.";

    public NotFoundOrderException() {
        super(MESSAGE);
    }
}
