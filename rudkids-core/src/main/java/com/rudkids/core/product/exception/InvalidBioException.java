package com.rudkids.core.product.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidBioException extends BadRequestException {
    private static final String MESSAGE = "잘못된 소개글 형식입니다.";

    public InvalidBioException() {
        super(MESSAGE);
    }
}
