package com.rudkids.core.cart.dto;

import com.rudkids.core.cart.domain.CartItemOption;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class CartRequest {

    @Builder
    public record AddCartItem(
        UUID itemId,
        int amount,
        List<AddCartItemOptionGroup> optionGroups
    ) {}

    @Builder
    public record AddCartItemOptionGroup(String name, AddCartItemOption option) {
        public CartItemOption toCartItemOption() {
            return CartItemOption.create(option.name, option.price);
        }
    }

    public record AddCartItemOption(String name, int price) {}

    @Builder
    public record UpdateCartItemAmount(UUID cartItemId, int amount) {}

    @Builder
    public record DeleteCartItems(List<UUID> cartItemIds) {}
}