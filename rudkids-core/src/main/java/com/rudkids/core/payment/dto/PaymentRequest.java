package com.rudkids.core.payment.dto;

import java.util.UUID;

public class PaymentRequest {

    public record Confirm(
        String paymentKey,
        UUID orderId,
        int amount
    ) {}

    public record Cancel(
        String paymentKey,
        String cancelReason,
        String bankCode,
        String refundAccountNumber,
        String refundAccountHolderName
    ) {}
}
