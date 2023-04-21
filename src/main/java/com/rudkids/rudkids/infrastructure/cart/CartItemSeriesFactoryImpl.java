package com.rudkids.rudkids.infrastructure.cart;

import com.rudkids.rudkids.domain.cart.CartCommand;
import com.rudkids.rudkids.domain.cart.CartItemSeriesFactory;
import com.rudkids.rudkids.domain.cart.domain.CartItem;
import com.rudkids.rudkids.domain.cart.domain.CartItemOption;
import com.rudkids.rudkids.domain.cart.domain.CartItemOptionGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemSeriesFactoryImpl implements CartItemSeriesFactory {

    @Override
    public void store(CartItem cartItem, List<CartCommand.AddCartItemOptionGroup> cartItemOptionGroups) {
        cartItemOptionGroups.forEach(optionGroup -> {
            CartItemOption cartItemOption = CartItemOption.create(optionGroup.option().name());
            var cartItemOptionGroup = CartItemOptionGroup.builder()
                    .cartItem(cartItem)
                    .name(optionGroup.name())
                    .cartItemOption(cartItemOption)
                    .build();

            cartItem.addOptionPrice(optionGroup.option().price());
            cartItem.addCartItemOptionGroup(cartItemOptionGroup);
        });
    }
}
