package com.rudkids.rudkids.domain.magazine.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidMagazineWriterException extends BadRequestException {
    private static final String MESSAGE = "잘못된 작성자입니다.";

    public InvalidMagazineWriterException() {
        super(MESSAGE);
    }
}
