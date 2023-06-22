package com.rudkids.core.payment.service;

import com.rudkids.core.payment.dto.PaymentRequest;

public interface PaymentService {
    void validateAndConfirm(PaymentRequest.Confirm request);
}
