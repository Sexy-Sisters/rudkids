package com.rudkids.core.verification.exception;

import com.rudkids.core.common.exception.InternalException;

public class MessageSendFailException extends InternalException {
    private static final String MESSAGE = "메세지 전송이 실패했습니다.";

    public MessageSendFailException() {
        super(MESSAGE);
    }
}
