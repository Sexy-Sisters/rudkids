package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.application.CartItemReader;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.exception.CartItemNotFoundException;
import com.rudkids.rudkids.domain.cart.repository.CartItemRepository;
import com.rudkids.rudkids.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartItemReaderImpl implements CartItemReader {
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem getCartItem(Cart cart, Item item, int amount) {
        return cartItemRepository.findByCartAndItem(cart, item)
                .orElseGet(() -> createCartItem(cart, item, amount));
    }

    private CartItem createCartItem(Cart cart, Item item, int amount) {
        var cartItem = CartItem.create(cart, item, amount);
        cart.addCartItem(cartItem);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem getCartItem(UUID cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(CartItemNotFoundException::new);
    }
}
