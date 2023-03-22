package com.rudkids.rudkids.domain.product.exception;

public class InvalidProductTitleException extends RuntimeException {
    private static final String MESSAGE = "프로덕트 제목이 잘못된 형식입니다.";

    public InvalidProductTitleException() {
        super(MESSAGE);
    }
}
