package com.rudkids.core.product.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    private static final String MESSAGE = "프로덕트를 찾을 수 없습니다.";

    public ProductNotFoundException() {
        super(MESSAGE);
    }
}
