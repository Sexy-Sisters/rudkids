package com.rudkids.core.product.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class DuplicateProductTitleException extends BadRequestException {
    private static final String MESSAGE = "중복된 제목입니다.";

    public DuplicateProductTitleException() {
        super(MESSAGE);
    }
}
