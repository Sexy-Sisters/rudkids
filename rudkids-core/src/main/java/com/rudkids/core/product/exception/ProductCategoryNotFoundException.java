package com.rudkids.core.product.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class ProductCategoryNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 상품 카테고리입니다.";

    public ProductCategoryNotFoundException() {
        super(MESSAGE);
    }
}
