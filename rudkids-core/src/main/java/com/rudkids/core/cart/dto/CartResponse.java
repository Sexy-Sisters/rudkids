package com.rudkids.core.cart.dto;

import java.util.List;

public class CartResponse {

    public record Main(
        int totalCartItemPrice,
        List<CartItemResponse.Main> cartItems
    ) {}

    public record Select(
        int totalPrice,
        String orderName,
        List<CartItemResponse.Select> selectedCartItems
    ) {}
}
