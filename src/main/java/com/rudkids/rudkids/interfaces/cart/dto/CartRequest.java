package com.rudkids.rudkids.interfaces.cart.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class CartRequest {

    @Builder
    public record AddCartItem(
            UUID itemId,
            List<AddCartItemOptionGroup> optionGroups,
            int amount) {
    }

    @Builder
    public record AddCartItemOptionGroup(String name, CartItemOption cartItemOption) {
    }

    public record CartItemOption(String name, int price) {
    }

    public record UpdateCartItemAmount(
            UUID cartId,
            UUID cartItemId,
            int amount
    ) {
    }

    public record DeleteCartItems(UUID cartId, List<UUID> cartItemIds) {
    }
}
