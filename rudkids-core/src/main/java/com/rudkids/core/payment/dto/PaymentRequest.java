package com.rudkids.core.payment.dto;

import java.util.UUID;

public class PaymentRequest {

    public record Confirm(
        String paymentKey,
        UUID orderId,
        int amount
    ) {}
}
