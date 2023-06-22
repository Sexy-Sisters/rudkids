package com.rudkids.core.item.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidItemOptionNameException extends BadRequestException {
    private static final String MESSAGE = "잘못된 아이템 옵션 이름입니다.";

    public InvalidItemOptionNameException() {
        super(MESSAGE);
    }
}
