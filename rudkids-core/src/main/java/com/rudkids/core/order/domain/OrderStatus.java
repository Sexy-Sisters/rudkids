package com.rudkids.core.order.domain;

import com.rudkids.core.order.exception.OrderStatusNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    INIT("주문시작"),
    ORDER_COMPLETE("주문완료"),
    DELIVERY_PREPARE("배송준비"),
    IN_DELIVERY("배송중"),
    DELIVERY_COMPLETE("배송완료"),
    CANCEL("주문취소");

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
