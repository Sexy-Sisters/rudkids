package com.rudkids.core.order.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderDeliveryStatus {
    READY("배송준비"),
    ING("배송중"),
    COMP("배송완료");

    private final String description;
}