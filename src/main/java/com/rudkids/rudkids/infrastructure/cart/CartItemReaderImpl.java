package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.application.CartItemReader;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.repository.CartItemRepository;
import com.rudkids.rudkids.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartItemReaderImpl implements CartItemReader {
    private final CartItemRepository cartItemRepository;

    @Override
    public Optional<CartItem> findCartItem(Cart cart, Item item) {
        return cartItemRepository.findByCartAndItem(cart, item);
    }
}
