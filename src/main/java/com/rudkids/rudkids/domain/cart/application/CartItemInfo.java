package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import lombok.Builder;

public class CartItemInfo {

    @Builder
    public record Main(
            String name,
            int price,
            ItemStatus itemStatus
    ) {
    }
}
