package com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOption;

import com.rudkids.rudkids.domain.order.exception.InvalidOrderItemOptionPriceException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class OrderItemOptionPrice {
    private static final int MIN_PRICE = 0;

    @Column(name = "price")
    private int value;

    protected OrderItemOptionPrice() {
    }

    private OrderItemOptionPrice(int value) {
        this.value = value;
    }

    public static OrderItemOptionPrice create(int value) {
        validate(value);
        return new OrderItemOptionPrice(value);
    }

    private static void validate(int value) {
        if(value < MIN_PRICE) {
            throw new InvalidOrderItemOptionPriceException();
        }
    }
}
