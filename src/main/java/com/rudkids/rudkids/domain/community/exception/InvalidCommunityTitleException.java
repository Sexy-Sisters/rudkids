package com.rudkids.rudkids.domain.community.exception;

import com.rudkids.rudkids.global.error.exception.BadRequestException;

public class InvalidCommunityTitleException extends BadRequestException {
    private static final String MESSAGE = "잘못된 제목입니다.";

    public InvalidCommunityTitleException() {
        super(MESSAGE);
    }
}
