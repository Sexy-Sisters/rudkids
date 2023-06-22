package com.rudkids.core.image.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class FileNameEmptyException extends BadRequestException {
    private static final String MESSAGE = "파일 이름이 비어있습니다.";

    public FileNameEmptyException() {
        super(MESSAGE);
    }
}
