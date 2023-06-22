package com.rudkids.core.product.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidProductTitleException extends BadRequestException {
    private static final String MESSAGE = "프로덕트 제목이 잘못된 형식입니다.";

    public InvalidProductTitleException() {
        super(MESSAGE);
    }
}
