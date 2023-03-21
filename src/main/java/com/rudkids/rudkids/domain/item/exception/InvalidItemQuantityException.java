package com.rudkids.rudkids.domain.item.exception;

public class InvalidItemQuantityException extends RuntimeException {
    private static final String MESSAGE = "잘못된 수량입니다.";

    public InvalidItemQuantityException() {
        super(MESSAGE);
    }
}
