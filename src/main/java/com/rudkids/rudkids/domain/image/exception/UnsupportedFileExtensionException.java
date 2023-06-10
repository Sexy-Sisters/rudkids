package com.rudkids.rudkids.domain.image.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class UnsupportedFileExtensionException extends BadRequestException {
    private static final String MESSAGE = "지원하지 않는 확장자 파일입니다.";

    public UnsupportedFileExtensionException() {
        super(MESSAGE);
    }
}
