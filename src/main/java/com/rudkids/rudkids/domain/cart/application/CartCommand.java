package com.rudkids.rudkids.domain.cart.application;

import lombok.Builder;

import java.util.UUID;

public class CartCommand {

    @Builder
    public record AddCartItem(UUID cartId, int amount) {
    }
}
