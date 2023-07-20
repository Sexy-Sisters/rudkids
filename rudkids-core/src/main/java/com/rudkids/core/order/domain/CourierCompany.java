package com.rudkids.core.order.domain;

import com.rudkids.core.order.exception.DeliveryTrackingNumberNotFoundException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourierCompany {
    private static final String DELIVERY_COMPLETE_STATE = "배송완료";
    private static final String TRACKING_NUMBER_NOT_FOUND_MESSAGE = "운송장 정보를 찾을 수 없습니다.";
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

    public void validateDeliveryTrackingNumber() {
        if(value.equals(TRACKING_NUMBER_NOT_FOUND_MESSAGE)) {
            throw new DeliveryTrackingNumberNotFoundException();
        }
    }
}
