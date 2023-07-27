package com.rudkids.core.order.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class NoSuchOAuthTokenException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 OAuth Token입니다.";

    public NoSuchOAuthTokenException() {
        super(MESSAGE);
    }
}
