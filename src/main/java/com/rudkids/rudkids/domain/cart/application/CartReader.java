package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.cart.domain.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartReader {
    Optional<Cart> findCart(UUID userId);
}
