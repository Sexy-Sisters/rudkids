package com.rudkids.core.cart.exception;

import com.rudkids.core.common.exception.ConflictException;

public class CannotAddComingSoonItemException extends ConflictException {
    private static final String MESSAGE = "준비중인 아이템은 장바구니에 담을 수 없습니다.";

    public CannotAddComingSoonItemException() {
        super(MESSAGE);
    }
}
