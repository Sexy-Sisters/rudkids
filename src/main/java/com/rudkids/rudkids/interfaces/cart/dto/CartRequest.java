package com.rudkids.rudkids.interfaces.cart.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class CartRequest {

    @Builder
    public record AddCartItem(
        UUID itemId,
        int amount,
        List<AddCartItemOptionGroup> optionGroups
    ) {
    }

    @Builder
    public record AddCartItemOptionGroup(String name, AddCartItemOption option) {
    }

    public record AddCartItemOption(String name, int price) {
    }

    public record UpdateCartItemAmount(
            UUID cartItemId,
            int amount
    ) {
    }

    public record DeleteCartItems(List<UUID> cartItemIds) {
    }
}
