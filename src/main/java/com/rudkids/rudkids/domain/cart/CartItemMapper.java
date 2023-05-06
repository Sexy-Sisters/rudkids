package com.rudkids.rudkids.domain.cart;

import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.domain.CartItemOptionGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemMapper {

    public CartItemInfo.Main toInfo(CartItem cartItem) {
        List<CartItemInfo.CartItemOptionGroup> optionGroups = cartItem.getCartItemOptionGroups().stream()
            .map(this::toInfo)
            .toList();

        return CartItemInfo.Main.builder()
            .id(cartItem.getId())
            .name(cartItem.getName())
            .price(cartItem.getPrice())
            .amount(cartItem.getAmount())
            .optionGroups(optionGroups)
            .itemStatus(cartItem.getItemStatus())
            .build();
    }

    private CartItemInfo.CartItemOptionGroup toInfo(CartItemOptionGroup cartItemOptionGroup) {
        return CartItemInfo.CartItemOptionGroup.builder()
            .name(cartItemOptionGroup.getName())
            .optionName(cartItemOptionGroup.getOptionName())
            .build();
    }
}
