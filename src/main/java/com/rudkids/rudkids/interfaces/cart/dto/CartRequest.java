package com.rudkids.rudkids.interfaces.cart.dto;

import java.util.List;
import java.util.UUID;

public class CartRequest {

    public record AddCartItem(UUID itemId, int amount) {
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
