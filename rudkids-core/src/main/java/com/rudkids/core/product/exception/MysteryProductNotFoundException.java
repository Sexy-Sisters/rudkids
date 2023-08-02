package com.rudkids.core.product.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class MysteryProductNotFoundException extends NotFoundException {
    private static final String MESSAGE = "미스테리 프로덕트가 존재하지 않습니다.";

    public MysteryProductNotFoundException() {
        super(MESSAGE);
    }
}
