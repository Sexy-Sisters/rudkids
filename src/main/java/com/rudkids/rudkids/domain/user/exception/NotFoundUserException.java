package com.rudkids.rudkids.domain.user.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class NotFoundUserException extends NotFoundException {
    private static final String MESSAGE = "유저가 존재하지 않습니다.";

    public NotFoundUserException() {
        super(MESSAGE);
    }
}
