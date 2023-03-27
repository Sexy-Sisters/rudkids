package com.rudkids.rudkids.domain.item.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidItemNameException extends BadRequestException {
    private static final String MESSAGE = "잘못된 아이템 이름입니다.";

    public InvalidItemNameException() {
        super(MESSAGE);
    }
}
