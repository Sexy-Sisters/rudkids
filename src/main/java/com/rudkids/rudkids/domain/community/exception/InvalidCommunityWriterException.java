package com.rudkids.rudkids.domain.community.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidCommunityWriterException extends BadRequestException {
    private static final String MESSAGE = "잘못된 작성자입니다.";

    public InvalidCommunityWriterException() {
        super(MESSAGE);
    }
}
