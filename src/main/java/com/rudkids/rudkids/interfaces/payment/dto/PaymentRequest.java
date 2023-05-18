package com.rudkids.rudkids.interfaces.payment.dto;

import java.util.UUID;

public class PaymentRequest {

    public record Confirm(
       String paymentKey,
       UUID orderId,
       int amount
    ) {
    }
}
