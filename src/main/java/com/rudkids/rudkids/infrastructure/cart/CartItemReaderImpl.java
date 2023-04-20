package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.CartItemReader;
import com.rudkids.rudkids.domain.cart.CartItemSeriesFactory;
import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.exception.CartItemNotFoundException;
import com.rudkids.rudkids.domain.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartItemReaderImpl implements CartItemReader {
    private final CartItemRepository cartItemRepository;
    private final CartItemSeriesFactory cartItemSeriesFactory;

    @Override
    public CartItem getCartItem(Cart cart, Item item, CartCommand.AddCartItem command) {
        return cartItemRepository.findByCartAndItem(cart, item)
                .orElseGet(() -> createCartItem(cart, item, command));
    }

    private CartItem createCartItem(Cart cart, Item item, CartCommand.AddCartItem command) {
        var cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .amount(command.amount())
                .price(item.getPrice())
                .build();
        cart.addCartItem(cartItem);

        cartItemSeriesFactory.store(cartItem, command.cartItemOptionGroups());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem getCartItem(UUID cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(CartItemNotFoundException::new);
    }
}
