package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.application.CartReader;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.repository.CartRepository;
import com.rudkids.rudkids.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartReaderImpl implements CartReader {
    private final CartRepository cartRepository;

    @Override
    public Cart getCart(User user) {
        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> createCart(user));
    }

    private Cart createCart(User user) {
        var cart = Cart.create(user);
        return cartRepository.save(cart);
    }
}
