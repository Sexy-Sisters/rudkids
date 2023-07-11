package com.rudkids.core.order.dto;

import com.rudkids.core.order.domain.PayMethod;

import java.util.UUID;

public class OrderRequest {

    public record Create(
        UUID deliveryId,
        PayMethod payMethod,
        String paymentKey,
        int amount
    ) {}

    public record ChangeStatus(String status) {}

    public record Cancel(
        String paymentKey,
        String cancelReason,
        String bankCode,
        String refundAccountNumber,
        String refundAccountHolderName
    ) {}
}
