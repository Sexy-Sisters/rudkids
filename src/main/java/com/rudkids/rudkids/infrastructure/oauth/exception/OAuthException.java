package com.rudkids.rudkids.infrastructure.oauth.exception;

import com.rudkids.rudkids.global.error.exception.InternalException;

public class OAuthException extends InternalException {
    private static final String MESSAGE = "OAuth서버 통신에서 문제가 발생했습니다.";

    public OAuthException() {
        super(MESSAGE);
    }
}
