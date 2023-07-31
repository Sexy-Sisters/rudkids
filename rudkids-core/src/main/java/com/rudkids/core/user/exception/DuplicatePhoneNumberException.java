package com.rudkids.core.user.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class DuplicatePhoneNumberException extends BadRequestException {
    private static final String MESSAGE = "중복된 폰 번호입니다.";

    public DuplicatePhoneNumberException() {
        super(MESSAGE);
    }
}
