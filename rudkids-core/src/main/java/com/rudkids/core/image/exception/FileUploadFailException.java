package com.rudkids.core.image.exception;

import com.rudkids.core.common.exception.InternalException;

public class FileUploadFailException extends InternalException {
    private static final String MESSAGE = "파일 업로드가 실패했습니다.";

    public FileUploadFailException() {
        super(MESSAGE);
    }
}
