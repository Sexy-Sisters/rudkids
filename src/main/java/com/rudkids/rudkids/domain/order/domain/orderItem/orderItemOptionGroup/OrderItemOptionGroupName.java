package com.rudkids.rudkids.domain.order.domain.orderItem.orderItemOptionGroup;

import com.rudkids.rudkids.domain.order.exception.InvalidOrderItemOptionGroupNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class OrderItemOptionGroupName {
    private static final int MAX_LENGTH = 20;

    @Column(name = "name", unique = true)
    private String value;

    protected OrderItemOptionGroupName() {
    }

    private OrderItemOptionGroupName(String value) {
        this.value = value;
    }

    public static OrderItemOptionGroupName create(String value) {
        validate(value);
        return new OrderItemOptionGroupName(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidOrderItemOptionGroupNameException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidOrderItemOptionGroupNameException();
        }
    }
}
