package com.rudkids.rudkids.domain.item.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ItemStatus {
    PREPARING("판매준비중"),
    SELLING("판매중"),
    SOLD_OUT("판매종료");
    private final String description;
}
