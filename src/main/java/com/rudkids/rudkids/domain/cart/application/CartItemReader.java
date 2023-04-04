package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.item.domain.Item;

import java.util.Optional;

public interface CartItemReader {

    Optional<CartItem> findCartItem(Cart cart, Item item);
}