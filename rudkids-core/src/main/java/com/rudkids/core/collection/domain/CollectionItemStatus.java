package com.rudkids.core.collection.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CollectionItemStatus {

    PUBLIC("구매한 상품"),
    PRIVATE("구매하지 않은 상품");

    private final String description;
}
