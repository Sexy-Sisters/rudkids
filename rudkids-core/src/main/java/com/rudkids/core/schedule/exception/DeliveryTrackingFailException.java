package com.rudkids.core.schedule.exception;

import com.rudkids.core.common.exception.InternalException;

public class DeliveryTrackingFailException extends InternalException {
    private static final String MESSAGE = "배송조회에 실패했습니다.";

    public DeliveryTrackingFailException() {
        super(MESSAGE);
    }
}
