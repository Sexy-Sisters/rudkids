package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.CartStore;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartStoreImpl implements CartStore {
    private final CartRepository cartRepository;

    @Override
    public Cart store(Cart cart) {
        return cartRepository.save(cart);
    }
}
