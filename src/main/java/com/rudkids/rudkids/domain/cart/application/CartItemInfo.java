package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import lombok.Builder;

import java.util.UUID;

public class CartItemInfo {

    @Builder
    public record Main(
            UUID id,
            String name,
            int price,
            ItemStatus itemStatus
    ) {
    }
}
