package com.rudkids.core.cart.service;

import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.dto.CartResponse;

import java.util.UUID;

public interface CartService {
    UUID addCartItem(UUID userId, CartRequest.AddCartItem request);
    CartResponse.Main getCartItems(UUID userId);
    CartResponse.Select getSelected(UUID userId);
    void updateCartItemAmount(UUID userId, CartRequest.UpdateCartItemAmount request);
    void select(UUID userId, CartRequest.SelectCartItem request);
    void deleteCartItem(UUID userId, UUID cartItemId);
}
