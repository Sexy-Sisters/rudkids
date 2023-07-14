package com.rudkids.core.order.dto;

import java.util.UUID;

public class OrderRequest {

    public record Create(
        UUID deliveryId,
        String paymentMethod
    ) {}

    public record ChangeStatus(String status) {}
}
