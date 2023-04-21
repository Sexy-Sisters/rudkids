package com.rudkids.rudkids.domain.cart;

import com.rudkids.rudkids.domain.cart.domain.CartItemOption;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class CartCommand {

    @Builder
    public record AddCartItem(
            UUID itemId,
            int amount,
            List<AddCartItemOptionGroup> optionGroups
    ) {
    }

    @Builder
    public record AddCartItemOptionGroup(String name, AddCartItemOption option) {
        public CartItemOption toCartItemOption() {
            return CartItemOption.create(option.name);
        }
    }

    @Builder
    public record AddCartItemOption(String name, int price) {
    }

    @Builder
    public record UpdateCartItemAmount(
            UUID cartId,
            UUID cartItemId,
            int amount
    ) {
    }

    @Builder
    public record DeleteCartItems(UUID cartId, List<UUID> cartItemIds) {
    }
}
