package com.rudkids.rudkids.domain.item.exception;

public class InvalidItemPriceException extends RuntimeException {
    private static final String MESSAGE = "잘못된 가격입니다.";

    public InvalidItemPriceException() {
        super(MESSAGE);
    }
}
