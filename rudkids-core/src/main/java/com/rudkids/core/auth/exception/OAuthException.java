package com.rudkids.core.auth.exception;

import com.rudkids.core.common.exception.InternalException;

public class OAuthException extends InternalException {
    private static final String MESSAGE = "OAuth서버 통신에서 문제가 발생했습니다.";

    public OAuthException() {
        super(MESSAGE);
    }
}
