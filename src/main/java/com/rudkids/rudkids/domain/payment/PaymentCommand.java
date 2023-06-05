package com.rudkids.rudkids.domain.payment;

import lombok.Builder;

import java.util.UUID;

public class PaymentCommand {

    @Builder
    public record Confirm(
        String paymentKey,
        UUID orderId,
        int amount
    ) {
    }
}
