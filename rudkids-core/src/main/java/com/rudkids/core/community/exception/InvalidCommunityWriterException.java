package com.rudkids.core.community.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidCommunityWriterException extends BadRequestException {
    private static final String MESSAGE = "잘못된 작성자입니다.";

    public InvalidCommunityWriterException() {
        super(MESSAGE);
    }
}
