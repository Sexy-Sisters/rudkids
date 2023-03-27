package com.rudkids.rudkids.domain.product.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidBioException extends BadRequestException {
    private static final String MESSAGE = "잘못된 소개글 형식입니다.";

    public InvalidBioException() {
        super(MESSAGE);
    }
}
