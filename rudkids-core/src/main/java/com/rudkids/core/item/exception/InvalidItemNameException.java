package com.rudkids.core.item.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidItemNameException extends BadRequestException {
    private static final String MESSAGE = "잘못된 아이템 이름입니다.";

    public InvalidItemNameException() {
        super(MESSAGE);
    }
}
