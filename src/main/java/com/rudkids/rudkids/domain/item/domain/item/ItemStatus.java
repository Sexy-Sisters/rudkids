package com.rudkids.rudkids.domain.item.domain.item;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ItemStatus {
    PREPARE("판매준비중"),
    ON_SALES("판매중"),
    END_OF_SALES("판매종료");
    private final String description;
}
