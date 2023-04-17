package com.rudkids.rudkids.domain.magazine.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidMagazineTitleException extends BadRequestException {
    private static final String MESSAGE = "잘못된 제목입니다.";

    public InvalidMagazineTitleException() {
        super(MESSAGE);
    }
}
