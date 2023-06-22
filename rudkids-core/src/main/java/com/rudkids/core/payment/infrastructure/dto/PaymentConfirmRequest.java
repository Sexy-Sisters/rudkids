package com.rudkids.core.payment.infrastructure.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record PaymentConfirmRequest(
    String paymentKey,
    int amount,
    UUID orderId
) {
}
