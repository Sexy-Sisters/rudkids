package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.application.CartItemStore;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemStoreImpl implements CartItemStore {
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem store(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}
