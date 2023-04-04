package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.application.CartReader;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartReaderImpl implements CartReader {
    private final CartRepository cartRepository;

    @Override
    public Optional<Cart> getCart(UUID userId) {
        return cartRepository.findByUserId(userId);
    }
}
