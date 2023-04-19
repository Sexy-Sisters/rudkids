package com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOption;

import com.rudkids.rudkids.domain.order.exception.InvalidOrderItemOptionNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class OrderItemOptionName {
    private static final int MAX_LENGTH = 20;

    @Column(name = "name", unique = true)
    private String value;

    protected OrderItemOptionName() {
    }

    private OrderItemOptionName(String value) {
        this.value = value;
    }

    public static OrderItemOptionName create(String value) {
        validate(value);
        return new OrderItemOptionName(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidOrderItemOptionNameException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidOrderItemOptionNameException();
        }
    }
}
