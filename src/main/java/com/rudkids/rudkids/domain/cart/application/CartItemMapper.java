package com.rudkids.rudkids.domain.cart.application;

import com.rudkids.rudkids.domain.cart.domain.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemInfo.Main toMain(CartItem cartItem) {
        return CartItemInfo.Main.builder()
                .name(cartItem.getName())
                .price(cartItem.getPrice())
                .itemStatus(cartItem.getItemStatus())
                .build();
    }
}
