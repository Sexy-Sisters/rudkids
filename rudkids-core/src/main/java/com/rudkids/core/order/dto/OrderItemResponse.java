package com.rudkids.core.order.dto;

import com.rudkids.core.order.domain.OrderItem;

public record OrderItemResponse(
    String imageUrl,
    String name,
    int amount,
    int price
) {
    public OrderItemResponse(OrderItem orderItem) {
        this(
            orderItem.getImageUrl(),
            orderItem.getName(),
            orderItem.getAmount(),
            orderItem.getPrice()
        );
    }
}
