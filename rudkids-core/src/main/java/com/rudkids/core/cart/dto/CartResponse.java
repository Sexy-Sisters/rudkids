package com.rudkids.core.cart.dto;

import java.util.List;

public class CartResponse {

    public record Select(
        int totalPrice,
        String orderName,
        List<CartItemResponse.Select> selectedCartItems
    ) {}
}
