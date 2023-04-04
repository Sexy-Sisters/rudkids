package com.rudkids.rudkids.domain.cart.application;

import java.util.UUID;

public interface CartService {
    void addCartItem(UUID id, CartCommand.AddCartItem command);
}
