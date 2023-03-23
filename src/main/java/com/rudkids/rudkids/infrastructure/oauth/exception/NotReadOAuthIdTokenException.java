package com.rudkids.rudkids.infrastructure.oauth.exception;

public class NotReadOAuthIdTokenException extends RuntimeException {
    private static final String MESSAGE = "id토큰을 읽을 수 없습니다.";

    public NotReadOAuthIdTokenException() {
        super(MESSAGE);
    }
}
