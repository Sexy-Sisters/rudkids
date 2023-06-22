package com.rudkids.core.cart.dto;

import com.rudkids.core.cart.domain.CartItem;
import com.rudkids.core.cart.domain.CartItemOptionGroup;
import com.rudkids.core.item.domain.ItemStatus;

import java.util.List;
import java.util.UUID;

public class CartItemResponse {

    public record Main(
        UUID id,
        String imageUrl,
        String name,
        int price,
        int amount,
        List<MainOptionGroup> optionGroups,
        ItemStatus itemStatus
    ) {
        public Main(CartItem cartItem) {
            this(
                cartItem.getId(),
                cartItem.getImageUrl(),
                cartItem.getName(),
                cartItem.getPrice(),
                cartItem.getAmount(),
                getOptionGroups(cartItem),
                cartItem.getItemStatus()
            );
        }

        private static List<MainOptionGroup> getOptionGroups(CartItem cartItem) {
            return cartItem.getCartItemOptionGroups().stream()
                .map(MainOptionGroup::new)
                .toList();
        }
    }

    public record MainOptionGroup(String name, String optionName) {
        public MainOptionGroup(CartItemOptionGroup optionGroup) {
            this(optionGroup.getName(), optionGroup.getOptionName());
        }
    }
}
