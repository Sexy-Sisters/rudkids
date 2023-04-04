package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.user.domain.User;

public interface CartReader {
    Cart getCart(User user);
}
