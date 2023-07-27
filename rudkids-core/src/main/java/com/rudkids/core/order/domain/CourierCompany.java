package com.rudkids.core.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourierCompany {
    private static final String DELIVERY_COMPLETE_STATE = "배송완료";
    private String value;

    private CourierCompany(String value) {
        this.value = value;
    }

    public static CourierCompany create(String value) {
        return new CourierCompany(value);
    }

    public boolean isCompletedState() {
        return value.equals(DELIVERY_COMPLETE_STATE);
    }
}
