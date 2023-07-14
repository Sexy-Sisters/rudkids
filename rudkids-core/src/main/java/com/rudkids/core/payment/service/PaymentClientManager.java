package com.rudkids.core.payment.service;

import com.rudkids.core.payment.dto.PaymentRequest;

public interface PaymentClientManager {

    void confirm(PaymentRequest.Confirm request);
    void cancel(PaymentRequest.Cancel request);
}
