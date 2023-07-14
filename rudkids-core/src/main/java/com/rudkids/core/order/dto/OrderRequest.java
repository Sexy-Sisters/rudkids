package com.rudkids.core.order.dto;

import com.rudkids.core.order.domain.PayMethod;

import java.util.UUID;

public class OrderRequest {

    public record Create(
        UUID deliveryId,
        PayMethod payMethod
    ) {}

    public record ChangeStatus(String status) {}
}
