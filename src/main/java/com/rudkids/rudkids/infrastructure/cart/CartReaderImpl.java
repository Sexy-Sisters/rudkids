package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.CartReader;
import com.rudkids.rudkids.domain.cart.CartStore;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartStatus;
import com.rudkids.rudkids.domain.cart.exception.CartNotFoundException;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartReaderImpl implements CartReader {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartStore cartStore;

    @Override
    public Cart getActiveCart(User user) {
        return cartRepository.findByUserIdAndCartStatus(user.getId(), CartStatus.ACTIVE)
            .orElseThrow(CartNotFoundException::new);
    }

    @Override
    public Cart getActiveCartOrCreate(User user) {
        return cartRepository.findByUserIdAndCartStatus(user.getId(), CartStatus.ACTIVE)
            .orElseGet(() -> createCart(user));
    }

    private Cart createCart(User user) {
        var cart = Cart.create(user);
        return cartStore.store(cart);
    }
}
