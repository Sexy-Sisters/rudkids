package com.rudkids.core.user.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class PhoneNumberEmptyException extends BadRequestException {
    private static final String MESSAGE = "전화번호가 비어있습니다.";

    public PhoneNumberEmptyException() {
        super(MESSAGE);
    }
}
