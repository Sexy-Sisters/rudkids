package com.rudkids.rudkids.domain.cart.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class CartItemNotFoundException extends NotFoundException {
    private static final String MESSAGE = "장바구니아이템이 존재하지 않습니다.";

    public CartItemNotFoundException() {
        super(MESSAGE);
    }
}
