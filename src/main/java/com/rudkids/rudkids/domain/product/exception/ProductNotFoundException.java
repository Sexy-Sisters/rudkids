package com.rudkids.rudkids.domain.product.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    private static final String MESSAGE = "프로덕트를 찾을 수 없습니다.";

    public ProductNotFoundException() {
        super(MESSAGE);
    }
}
