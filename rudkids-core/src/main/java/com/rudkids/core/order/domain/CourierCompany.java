package com.rudkids.core.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourierCompany {
    private static final String DELIVERY_COMPLETE_STATE = "배송완료";
    private String state;

    private CourierCompany(String state) {
        this.state = state;
    }

    public static CourierCompany create(String state) {
        return new CourierCompany(state);
    }

    public boolean isCompletedState() {
        return state.equals(DELIVERY_COMPLETE_STATE);
    }
}
