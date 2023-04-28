package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.CartReader;
import com.rudkids.rudkids.domain.cart.CartStore;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.exception.CartNotFoundException;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartReaderImpl implements CartReader {
    private final CartRepository cartRepository;
    private final CartStore cartStore;

    @Override
    public Cart getCartOrCreate(User user) {
        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> createCart(user));
    }

    private Cart createCart(User user) {
        var cart = Cart.create(user);
        return cartStore.store(cart);
    }

    @Override
    public Cart getCart(UUID id) {
        return cartRepository.findById(id)
                .orElseThrow(CartNotFoundException::new);
    }
}
