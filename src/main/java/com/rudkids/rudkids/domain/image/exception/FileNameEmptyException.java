package com.rudkids.rudkids.domain.image.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class FileNameEmptyException extends BadRequestException {
    private static final String MESSAGE = "파일 이름이 비어있습니다.";

    public FileNameEmptyException() {
        super(MESSAGE);
    }
}
