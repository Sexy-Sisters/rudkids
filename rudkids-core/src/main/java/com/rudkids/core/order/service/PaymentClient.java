package com.rudkids.core.order.service;

import com.rudkids.core.order.dto.OrderRequest;
import com.rudkids.core.order.infrastructure.dto.TossPaymentResponse;

public interface PaymentClient {
    TossPaymentResponse.Info confirm(final String paymentKey, final String orderId, final int amount);
    void cancelVirtualAccount(String paymentKey, OrderRequest.PaymentCancel request);
    void cancel(String paymentKey, String cancelReason);
}
