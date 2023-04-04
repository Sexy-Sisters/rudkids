package com.rudkids.rudkids.domain.cart.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class CartNotFoundException extends NotFoundException {
    private static final String MESSAGE = "장바구니가 존재하지 않습니다.";

    public CartNotFoundException() {
        super(MESSAGE);
    }
}
