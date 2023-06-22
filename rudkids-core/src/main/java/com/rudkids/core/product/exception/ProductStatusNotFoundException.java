package com.rudkids.core.product.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class ProductStatusNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 프로덕트 상태 값입니다.";

    public ProductStatusNotFoundException() {
        super(MESSAGE);
    }
}
