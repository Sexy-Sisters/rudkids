package com.rudkids.core.cart.domain;

import com.rudkids.core.user.domain.User;

public interface CartRepository {
    Cart getActiveCart(User user);
    Cart getActiveCartOrCreate(User user);
}
