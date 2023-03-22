package com.rudkids.rudkids.domain.item.exception;

public class InvalidItemNameException extends RuntimeException {
    private static final String MESSAGE = "잘못된 아이템 이름입니다.";

    public InvalidItemNameException() {
        super(MESSAGE);
    }
}
