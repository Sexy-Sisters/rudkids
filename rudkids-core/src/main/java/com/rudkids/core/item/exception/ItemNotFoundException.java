package com.rudkids.core.item.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class ItemNotFoundException extends NotFoundException {
    private static final String MESSAGE = "아이템을 찾을 수 없습니다.";

    public ItemNotFoundException() {
        super(MESSAGE);
    }
}
