package com.rudkids.rudkids.domain.cart;

import com.rudkids.rudkids.domain.cart.domain.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemInfo.Main toInfo(CartItem cartItem) {
        return CartItemInfo.Main.builder()
                .id(cartItem.getId())
                .name(cartItem.getName())
                .price(cartItem.getPrice())
                .itemStatus(cartItem.getItemStatus())
                .build();
    }
}
