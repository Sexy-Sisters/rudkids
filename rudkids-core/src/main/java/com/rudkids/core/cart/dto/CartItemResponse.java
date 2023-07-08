package com.rudkids.core.cart.dto;

import com.rudkids.core.cart.domain.CartItem;
import com.rudkids.core.item.domain.ItemStatus;

import java.util.UUID;

public class CartItemResponse {

    public record Main(
        UUID id,
        String imageUrl,
        String name,
        int price,
        int amount,
        ItemStatus itemStatus
    ) {
        public Main(CartItem cartItem) {
            this(
                cartItem.getId(),
                cartItem.getImageUrl(),
                cartItem.getName(),
                cartItem.getPrice(),
                cartItem.getAmount(),
                cartItem.getItemStatus()
            );
        }
    }

    public record Select(String name, int amount) {
        public Select(CartItem cartItem) {
            this(cartItem.getName(), cartItem.getAmount());
        }
    }
}
