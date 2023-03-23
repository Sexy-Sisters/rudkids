package com.rudkids.rudkids.domain.auth.exception;

public class EmptyAuthorizationHeaderException extends RuntimeException {
    private static final String MESSAGE = "헤더에 Authorization값이 존재하지 않습니다.";

    public EmptyAuthorizationHeaderException() {
        super(MESSAGE);
    }
}
