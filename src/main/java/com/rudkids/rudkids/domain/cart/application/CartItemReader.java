package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.cart.domain.Cart;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.item.domain.Item;

import java.util.UUID;

public interface CartItemReader {

    CartItem getCartItem(Cart cart, Item item, int amount);
    CartItem getCartItem(UUID cartItemId);
}