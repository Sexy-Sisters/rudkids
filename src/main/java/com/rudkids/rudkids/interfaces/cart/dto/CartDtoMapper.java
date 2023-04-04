package com.rudkids.rudkids.interfaces.cart.dto;

import com.rudkids.rudkids.domain.cart.application.CartCommand;
import org.springframework.stereotype.Component;

@Component
public class CartDtoMapper {

    public CartCommand.AddCartItem to(CartRequest.AddCartItem request) {
        return CartCommand.AddCartItem.builder()
                .itemId(request.itemId())
                .amount(request.amount())
                .build();
    }
}
