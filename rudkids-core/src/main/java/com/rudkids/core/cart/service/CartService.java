package com.rudkids.core.cart.service;

import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.cart.dto.CartResponse;

import java.util.UUID;

public interface CartService {
    UUID addCartItem(UUID userId, CartRequest.AddCartItem command);
    CartResponse.Main getCartItems(UUID userId);
    void updateCartItemAmount(UUID userId, CartRequest.UpdateCartItemAmount command);
    void deleteCartItems(UUID userId, CartRequest.DeleteCartItems command);
}
