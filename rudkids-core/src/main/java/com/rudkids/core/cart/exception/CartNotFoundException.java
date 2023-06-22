package com.rudkids.core.cart.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class CartNotFoundException extends NotFoundException {
    private static final String MESSAGE = "장바구니가 존재하지 않습니다.";

    public CartNotFoundException() {
        super(MESSAGE);
    }
}
