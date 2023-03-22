package com.rudkids.rudkids.domain.product.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductStatus {
    OPEN("진행중"), CLOSED("중단");
    private final String description;
}
