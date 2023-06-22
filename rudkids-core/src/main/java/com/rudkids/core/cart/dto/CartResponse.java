package com.rudkids.core.cart.dto;

import java.util.List;

public class CartResponse {

    public record Main(
        int totalCartItemPrice,
        List<CartItemResponse.Main> cartItems
    ) {}
}
