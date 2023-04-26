package com.rudkids.rudkids.domain.product.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class ProductStatusNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 프로덕트 상태 값입니다.";

    public ProductStatusNotFoundException() {
        super(MESSAGE);
    }
}
