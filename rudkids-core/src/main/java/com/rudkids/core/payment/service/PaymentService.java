package com.rudkids.core.payment.service;

import com.rudkids.core.payment.dto.PaymentRequest;
import com.rudkids.core.payment.dto.PaymentResponse;

import java.util.UUID;

public interface PaymentService {

    PaymentResponse.Info getInformation(UUID userId, UUID orderId);

    void confirm(PaymentRequest.Confirm request);

    void cancel(UUID orderId, PaymentRequest.Cancel request);
}
