package com.rudkids.rudkids.domain.item.domain.item;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LimitType {
    LIMITED("수량한정"), NORMAL("일반");
    private final String description;
}
