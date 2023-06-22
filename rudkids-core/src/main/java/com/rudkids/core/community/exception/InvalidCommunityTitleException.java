package com.rudkids.core.community.exception;

import com.rudkids.core.common.exception.BadRequestException;

public class InvalidCommunityTitleException extends BadRequestException {
    private static final String MESSAGE = "잘못된 제목입니다.";

    public InvalidCommunityTitleException() {
        super(MESSAGE);
    }
}
