package com.rudkids.rudkids.domain.cart;

import com.rudkids.rudkids.domain.cart.domain.CartItem;

import java.util.List;

public interface CartItemSeriesFactory {
    void store(CartItem cartItem, CartCommand.AddCartItem command);
}
