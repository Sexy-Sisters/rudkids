package com.rudkids.rudkids.domain.cart;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.user.domain.User;

import java.util.UUID;

public interface CartReader {
    Cart getCart(UUID id);
    Cart getActiveCart(User user);
    Cart getActiveCartOrCreate(User user);
}
