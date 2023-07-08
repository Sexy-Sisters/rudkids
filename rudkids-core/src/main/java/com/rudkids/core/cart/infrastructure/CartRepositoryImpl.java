package com.rudkids.core.cart.infrastructure;

import com.rudkids.core.cart.domain.Cart;
import com.rudkids.core.cart.domain.CartRepository;
import com.rudkids.core.cart.exception.CartNotFoundException;
import com.rudkids.core.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    private final JpaCartRepository cartRepository;

    @Override
    public Cart get(User user) {
        return cartRepository.findByUser(user)
            .orElseThrow(CartNotFoundException::new);
    }

    @Override
    public Cart getOrCreate(User user) {
        return cartRepository.findByUser(user)
            .orElseGet(() -> saveCart(user));
    }

    private Cart saveCart(User user) {
        var cart = Cart.create(user);
        return cartRepository.save(cart);
    }
}
