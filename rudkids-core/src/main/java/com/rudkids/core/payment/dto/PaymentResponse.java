package com.rudkids.core.payment.dto;

import java.util.UUID;

public class PaymentResponse {

    public record Info(
        UUID orderId,
        String orderName,
        UUID customerId,
        int amount
    ) {}
}
