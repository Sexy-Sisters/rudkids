package com.rudkids.rudkids.domain.auth.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class OAuthNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 OAuth입니다.";

    public OAuthNotFoundException() {
        super(MESSAGE);
    }
}
