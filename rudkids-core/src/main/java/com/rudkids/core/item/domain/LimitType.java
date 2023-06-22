package com.rudkids.core.item.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LimitType {
    LIMITED("수량한정"),
    NORMAL("일반");

    private final String description;
}
