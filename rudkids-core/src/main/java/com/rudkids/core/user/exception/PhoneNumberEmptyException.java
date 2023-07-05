package com.rudkids.core.user.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class PhoneNumberEmptyException extends NotFoundException {
    private static final String MESSAGE = "전화번호가 비어있습니다.";

    public PhoneNumberEmptyException() {
        super(MESSAGE);
    }
}
