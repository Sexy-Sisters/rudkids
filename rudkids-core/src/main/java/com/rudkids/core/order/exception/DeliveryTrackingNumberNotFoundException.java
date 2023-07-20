package com.rudkids.core.order.exception;

import com.rudkids.core.common.exception.NotFoundException;

public class DeliveryTrackingNumberNotFoundException extends NotFoundException {
    private static final String MESSAGE = "등록할 운송장 번호가 존재하지 않습니다.";

    public DeliveryTrackingNumberNotFoundException() {
        super(MESSAGE);
    }
}
