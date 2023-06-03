package com.rudkids.rudkids.domain.community.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidCommunityContentException extends BadRequestException {
    private static final String MESSAGE = "잘못된 내용 길이입니다.";

    public InvalidCommunityContentException() {
        super(MESSAGE);
    }
}
