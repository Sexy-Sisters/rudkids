package com.rudkids.rudkids.domain.cart;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class CartCommand {

    @Builder
    public record AddCartItem(
            UUID itemId,
            List<AddCartItemOptionGroup> cartItemOptionGroups,
            int amount) {
    }

    @Builder
    public record AddCartItemOptionGroup(
            String name,
            AddCartItemOption cartItemOption
    ) {
    }

    @Builder
    public record AddCartItemOption(
            String name,
            int price
    ) {
    }

    @Builder
    public record UpdateCartItemAmount(
            UUID cartId,
            UUID cartItemId,
            int amount
    ) {
    }

    @Builder
    public record DeleteCartItems(UUID cartId, List<UUID> cartItemIds) {
    }
}
