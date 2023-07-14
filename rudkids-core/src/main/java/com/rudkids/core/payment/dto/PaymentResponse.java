package com.rudkids.core.payment.dto;

import java.util.UUID;

public class PaymentResponse {

    public record WidgetInfo(
        UUID customerId,
        int amount
    ) {}

    public record Info(
        UUID orderId,
        String orderName,
        String customerName,
        String customerEmail
    ) {}
}
