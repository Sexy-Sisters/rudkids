package com.rudkids.rudkids.domain.cart;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class CartItemInfo {

    @Builder
    public record Main(
            UUID id,
            String imageUrl,
            String name,
            int price,
            int amount,
            List<CartItemOptionGroup> optionGroups,
            ItemStatus itemStatus
    ) {
    }

    @Builder
    public record CartItemOptionGroup(String name, String optionName) {
    }
}
