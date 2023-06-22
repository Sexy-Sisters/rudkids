package com.rudkids.core.auth.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class EmptyAuthorizationHeaderException extends BadRequestException {
    private static final String MESSAGE = "헤더에 Authorization값이 존재하지 않습니다.";

    public EmptyAuthorizationHeaderException() {
        super(MESSAGE);
    }
}
