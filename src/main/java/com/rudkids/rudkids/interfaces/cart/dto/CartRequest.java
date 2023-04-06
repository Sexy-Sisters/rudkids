package com.rudkids.rudkids.interfaces.cart.dto;

import java.util.UUID;

public class CartRequest {

    public record AddCartItem(UUID itemId, int amount) {
    }

    public record UpdateCartItemAmount(UUID cartItemId, int amount) {
    }
}
