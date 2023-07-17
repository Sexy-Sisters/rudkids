package com.rudkids.core.schedule.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryTrackState {
    private static final String DELIVERY_COMPLETE_STATE = "배송완료";
    private String state;

    private DeliveryTrackState(String state) {
        this.state = state;
    }

    public static DeliveryTrackState create(String state) {
        return new DeliveryTrackState(state);
    }

    public boolean isCompleteState() {
        return state.equals(DELIVERY_COMPLETE_STATE);
    }
}
