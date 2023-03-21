package com.rudkids.rudkids.domain.item.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ItemStatus {
    IN_STOCK("판매중"), SOLD_OUT("솔드아웃");
    private final String description;
}
