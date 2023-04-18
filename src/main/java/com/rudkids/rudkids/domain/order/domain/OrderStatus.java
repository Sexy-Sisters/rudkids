package com.rudkids.rudkids.domain.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    INIT("주문시작"),
    ORDER_COMPLETE("주문완료"),
    DELIVERY_PREPARE("배송준비"),
    IN_DELIVERY("배송중"),
    DELIVERY_COMPLETE("배송완료");

    private final String description;
}
