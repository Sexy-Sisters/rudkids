package com.rudkids.rudkids.domain.magazine.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidMagazineContentException extends BadRequestException {
    private static final String MESSAGE = "잘못된 내용 길이입니다.";

    public InvalidMagazineContentException() {
        super(MESSAGE);
    }
}
