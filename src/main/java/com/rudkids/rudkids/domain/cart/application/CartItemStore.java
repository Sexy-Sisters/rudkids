package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.cart.domain.CartItem;

public interface CartItemStore {
    CartItem store(CartItem cartItem);
}
