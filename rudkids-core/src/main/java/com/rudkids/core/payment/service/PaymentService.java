package com.rudkids.core.payment.service;

import com.rudkids.core.payment.dto.PaymentRequest;

import java.util.UUID;

public interface PaymentService {
    void confirm(PaymentRequest.Confirm request);
    void cancel(UUID orderId, PaymentRequest.Cancel request);
}
