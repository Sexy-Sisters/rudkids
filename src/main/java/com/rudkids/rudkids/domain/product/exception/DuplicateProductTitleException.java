package com.rudkids.rudkids.domain.product.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class DuplicateProductTitleException extends BadRequestException {
    private static final String MESSAGE = "중복된 제목입니다.";

    public DuplicateProductTitleException() {
        super(MESSAGE);
    }
}
