package com.rudkids.core.order.dto;

import lombok.Builder;

public class TossPaymentRequest {

    @Builder
    public record Confirm(
        String paymentKey,
        String orderId,
        int amount
    ) {}

    @Builder
    public record Cancel(
        String cancelReason,
        RefundReceiveAccount refundReceiveAccount
    ) {}

    @Builder
    public record RefundReceiveAccount(
        String bank,
        String accountNumber,
        String holderName
    ) {}
}
