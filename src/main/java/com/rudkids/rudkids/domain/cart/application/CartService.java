package com.rudkids.rudkids.domain.cart.application;

import java.util.UUID;

public interface CartService {
    void addCartItem(UUID userId, CartCommand.AddCartItem command);
    CartInfo.Main findCartItems(UUID userId);
}
