package com.rudkids.core.delivery.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeliveryStatus {
    READY("배송준비"),
    IN("배송중"),
    COMP("배송완료");

    private final String description;
}
