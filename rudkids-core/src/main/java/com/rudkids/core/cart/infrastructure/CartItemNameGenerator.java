package com.rudkids.core.cart.infrastructure;

import com.rudkids.core.cart.dto.CartRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartItemNameGenerator {
    private static final String NAME_DELIMITER = ", ";

    public String generate(String itemName, CartRequest.AddCartItem request) {
        String cartItemName = createName(request);
        return itemName + NAME_DELIMITER + cartItemName;
    }

    public String createName(CartRequest.AddCartItem request) {
        return request.optionGroups().stream()
            .map(group -> group.name() + NAME_DELIMITER + group.option().name())
            .collect(Collectors.joining(NAME_DELIMITER));
    }
}
