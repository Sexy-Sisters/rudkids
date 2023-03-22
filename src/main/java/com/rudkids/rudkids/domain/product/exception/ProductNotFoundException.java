package com.rudkids.rudkids.domain.product.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final String MESSAGE = "프로덕트를 찾을 수 없습니다.";

    public ProductNotFoundException() {
        super(MESSAGE);
    }
}
