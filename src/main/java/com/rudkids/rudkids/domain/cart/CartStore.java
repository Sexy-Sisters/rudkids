package com.rudkids.rudkids.domain.cart;

import com.rudkids.rudkids.domain.cart.domain.Cart;

public interface CartStore {
    Cart store(Cart cart);
}
