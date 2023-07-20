package com.rudkids.core.order.infrastructure;

import com.rudkids.core.cart.domain.CartItem;
import com.rudkids.core.cart.dto.CartItemResponse;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Component
public class OrderNameGenerator {
    private static final String ORDER_ITEM_NAME_DELIMITER = ",";
    private static final String ORDER_NAME_FORMAT = " 외 {0}건";

    public String generate(List<CartItem> cartItems) {
        String name = split(cartItems.get(0).getName());
        String format = MessageFormat.format(ORDER_NAME_FORMAT, cartItems.size());
        return name + format;
    }

    private String split(String name) {
        return name.split(ORDER_ITEM_NAME_DELIMITER)[0];
    }
}
