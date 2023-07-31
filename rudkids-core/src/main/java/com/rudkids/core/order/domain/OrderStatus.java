package com.rudkids.core.order.domain;

import com.rudkids.core.order.exception.OrderStatusNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    DEPOSIT_READY("입금대기중"),
    ORDER("주문완료"),
    CANCEL_READY("취소대기중"),
    CANCEL("취소완료"),
    DELIVERY_READY("배송준비"),
    DELIVERY_ING("배송중"),
    DELIVERY_COMP("배송완료");

    private final String description;

    public static OrderStatus toEnum(String description) {
        return Arrays.stream(values())
            .filter(it -> it.isSameDescription(description))
            .findFirst()
            .orElseThrow(OrderStatusNotFoundException::new);
    }

    private boolean isSameDescription(String description) {
        return this.name().equals(description);
    }
}
