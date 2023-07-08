package com.rudkids.core.cart.domain;

import com.rudkids.core.cart.dto.CartRequest;
import com.rudkids.core.item.domain.Item;

import java.util.List;
import java.util.UUID;

public interface CartItemRepository {
    CartItem getOrCreate(Cart cart, Item item, CartRequest.AddCartItem request);
    CartItem get(UUID id);
    void selects(List<UUID> ids);
    void deleteSelected();
    void delete(CartItem cartItem);
}
