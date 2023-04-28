package com.rudkids.rudkids.domain.cart;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.user.domain.User;

import java.util.UUID;

public interface CartReader {
    Cart getCartOrCreate(User user);
    Cart getCart(UUID id);
}
