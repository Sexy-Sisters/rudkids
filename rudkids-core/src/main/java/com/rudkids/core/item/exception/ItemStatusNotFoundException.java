package com.rudkids.core.item.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class ItemStatusNotFoundException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 아이템 상태 값입니다.";

    public ItemStatusNotFoundException() {
        super(MESSAGE);
    }
}
