package com.rudkids.core.order.dto;

public class OrderRequest {

    public record OrderAndPayment(
        String paymentKey,
        String orderId,
        int amount
    ) {
    }

    public record ChangeStatus(String status) {
    }

    public record PaymentCancel(
        String cancelReason,
        String refundAccountNumber,
        String bankName,
        String refundAccountHolderName
    ) {
    }
}
