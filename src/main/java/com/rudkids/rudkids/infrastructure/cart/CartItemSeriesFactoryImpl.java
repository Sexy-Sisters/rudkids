package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.CartItemSeriesFactory;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.domain.CartItemOptionGroup;
import org.springframework.stereotype.Component;

@Component
public class CartItemSeriesFactoryImpl implements CartItemSeriesFactory {

    @Override
    public void store(CartItem cartItem, CartCommand.AddCartItem command) {
        command.optionGroups().forEach(optionGroup -> {
            var cartItemOptionGroup = CartItemOptionGroup.builder()
                    .cartItem(cartItem)
                    .name(optionGroup.name())
                    .cartItemOption(optionGroup.toCartItemOption())
                    .build();

            cartItem.addCartItemOptionGroup(cartItemOptionGroup);
        });
    }
}
