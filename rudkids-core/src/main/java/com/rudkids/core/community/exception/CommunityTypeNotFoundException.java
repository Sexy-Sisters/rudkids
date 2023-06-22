package com.rudkids.core.community.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class CommunityTypeNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 커뮤니티 타입입니다.";

    public CommunityTypeNotFoundException() {
        super(MESSAGE);
    }
}
