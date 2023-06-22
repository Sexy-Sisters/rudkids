package com.rudkids.core.community.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class CommunityNotFoundException extends NotFoundException {
    private static final String MASSGE = "존재하지 않는 매거진입니다.";

    public CommunityNotFoundException() {
        super(MASSGE);
    }
}
