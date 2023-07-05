package com.rudkids.core.auth.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class OAuthNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 OAuth 제공자 입니다.";

    public OAuthNotFoundException() {
        super(MESSAGE);
    }
}
