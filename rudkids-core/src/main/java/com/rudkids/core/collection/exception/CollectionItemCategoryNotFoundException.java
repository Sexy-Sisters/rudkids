package com.rudkids.core.collection.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class CollectionItemCategoryNotFoundException extends NotFoundException {
    private static final String MESSAGE = "컬렉션아이템 카테고리가 존재하지 않습니다.";

    public CollectionItemCategoryNotFoundException() {
        super(MESSAGE);
    }
}
