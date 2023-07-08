package com.rudkids.core.cart.domain;

import com.rudkids.core.user.domain.User;

public interface CartRepository {
    Cart get(User user);
    Cart getOrCreate(User user);
}
