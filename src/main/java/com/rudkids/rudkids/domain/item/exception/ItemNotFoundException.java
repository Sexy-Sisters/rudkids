package com.rudkids.rudkids.domain.item.exception;

public class ItemNotFoundException extends RuntimeException {
    private static final String MESSAGE = "아이템을 찾을 수 없습니다.";

    public ItemNotFoundException() {
        super(MESSAGE);
    }
}
