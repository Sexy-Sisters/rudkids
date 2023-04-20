package com.rudkids.rudkids.interfaces.cart.dto;

import com.rudkids.rudkids.domain.cart.CartCommand;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartDtoMapper {

    public CartCommand.AddCartItem to(CartRequest.AddCartItem request) {
        List<CartCommand.AddCartItemOptionGroup> cartItemOptionGroups = request.optionGroups().stream()
                .map(this::toCartItemOptionGroup)
                .collect(Collectors.toList());

        return CartCommand.AddCartItem.builder()
                .itemId(request.itemId())
                .cartItemOptionGroups(cartItemOptionGroups)
                .amount(request.amount())
                .build();
    }

    private CartCommand.AddCartItemOptionGroup toCartItemOptionGroup(CartRequest.AddCartItemOptionGroup optionGroup) {
        CartRequest.CartItemOption requestOption = optionGroup.cartItemOption();
        CartCommand.AddCartItemOption commandOption = CartCommand.AddCartItemOption.builder()
                .name(requestOption.name())
                .price(requestOption.price())
                .build();

        return CartCommand.AddCartItemOptionGroup.builder()
                .name(optionGroup.name())
                .cartItemOption(commandOption)
                .build();
    }

    public CartCommand.UpdateCartItemAmount to(CartRequest.UpdateCartItemAmount request) {
        return CartCommand.UpdateCartItemAmount.builder()
                .cartId(request.cartId())
                .cartItemId(request.cartItemId())
                .amount(request.amount())
                .build();
    }

    public CartCommand.DeleteCartItems to(CartRequest.DeleteCartItems request) {
        return CartCommand.DeleteCartItems.builder()
                .cartId(request.cartId())
                .cartItemIds(request.cartItemIds())
                .build();
    }
}
