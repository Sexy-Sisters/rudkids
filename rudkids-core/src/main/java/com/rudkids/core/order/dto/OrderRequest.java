package com.rudkids.core.order.dto;

import com.rudkids.core.order.domain.PayMethod;

import java.util.UUID;

public class OrderRequest {

    public record Create(
        PayMethod payMethod,
        UUID deliveryId
    ) {}

    public record ChangeStatus(String status) {}
}
