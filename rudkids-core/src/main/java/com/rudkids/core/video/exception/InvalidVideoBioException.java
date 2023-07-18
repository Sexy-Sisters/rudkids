package com.rudkids.core.video.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidVideoBioException extends BadRequestException {
    private static final String MESSAGE = "잘못된 비디오 소개글 형식입니다";

    public InvalidVideoBioException() {
        super(MESSAGE);
    }
}
