package com.rudkids.core.item.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class DuplicatedNameException extends BadRequestException {
    private static final String MESSAGE = "중복된 아이템 이름입니다.";

    public DuplicatedNameException() {
        super(MESSAGE);
    }
}
