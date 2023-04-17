package com.rudkids.rudkids.domain.magazine.exception;

import com.rudkids.rudkids.global.error.exception.NotFoundException;

public class MagazineNotFoundException extends NotFoundException {
    private static final String MASSGE = "존재하지 않는 매거진입니다.";

    public MagazineNotFoundException() {
        super(MASSGE);
    }
}
