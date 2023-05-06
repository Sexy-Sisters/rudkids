package com.rudkids.rudkids.interfaces.cart.dto;

import com.rudkids.rudkids.domain.cart.CartCommand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartDtoMapper {

    public CartCommand.AddCartItem to(CartRequest.AddCartItem request) {
        List<CartCommand.AddCartItemOptionGroup> optionGroups = request.optionGroups().stream()
                .map(this::toCommand)
                .toList();

        return CartCommand.AddCartItem.builder()
                .itemId(request.itemId())
                .optionGroups(optionGroups)
                .amount(request.amount())
                .build();
    }

    private CartCommand.AddCartItemOptionGroup toCommand(CartRequest.AddCartItemOptionGroup optionGroup) {
        return CartCommand.AddCartItemOptionGroup.builder()
                .name(optionGroup.name())
                .option(toCommand(optionGroup.option()))
                .build();
    }

    private CartCommand.AddCartItemOption toCommand(CartRequest.AddCartItemOption option) {
        return CartCommand.AddCartItemOption.builder()
                .name(option.name())
                .price(option.price())
                .build();
    }

    public CartCommand.UpdateCartItemAmount to(CartRequest.UpdateCartItemAmount request) {
        return CartCommand.UpdateCartItemAmount.builder()
                .cartItemId(request.cartItemId())
                .amount(request.amount())
                .build();
    }

    public CartCommand.DeleteCartItems to(CartRequest.DeleteCartItems request) {
        return CartCommand.DeleteCartItems.builder()
                .cartItemIds(request.cartItemIds())
                .build();
    }
}
