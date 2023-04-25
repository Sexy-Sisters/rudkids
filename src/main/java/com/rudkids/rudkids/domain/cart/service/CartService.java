package com.rudkids.rudkids.domain.cart.service;

import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.CartInfo;

import java.util.UUID;

public interface CartService {
    UUID addCartItem(UUID userId, CartCommand.AddCartItem command);
    CartInfo.Main findCartItems(UUID userId);
    void updateCartItemAmount(UUID userId, CartCommand.UpdateCartItemAmount command);
    void deleteCartItems(UUID userId, CartCommand.DeleteCartItems command);
}
