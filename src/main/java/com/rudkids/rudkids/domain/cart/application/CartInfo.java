package com.rudkids.rudkids.domain.cart.application;

import lombok.Builder;

import java.util.List;

public class CartInfo {

    @Builder
    public record Main(
            int totalCartItemPrice,
            List<CartItemInfo.Main> cartItems
    ) {
    }
}
