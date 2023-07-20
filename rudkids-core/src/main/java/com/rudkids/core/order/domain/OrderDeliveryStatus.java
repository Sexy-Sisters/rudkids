package com.rudkids.core.order.domain;

import com.rudkids.core.order.exception.OrderDeliveryStatusNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderDeliveryStatus {
    READY("배송준비"),
    ING("배송중"),
    COMP("배송완료");

    private final String description;

    public static OrderDeliveryStatus toEnum(String target) {
        return Arrays.stream(values())
            .filter(status -> status.isSameStatus(target))
            .findFirst()
            .orElseThrow(OrderDeliveryStatusNotFoundException::new);
    }

    private boolean isSameStatus(String target) {
        return this.name().equals(target);
    }
}