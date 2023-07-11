package com.rudkids.core.order.service;

import com.rudkids.core.order.dto.OrderRequest;

public interface PaymentClientManager {

    void confirm(String paymentKey, String orderId, int amount);
    void cancel(OrderRequest.Cancel request);
}
