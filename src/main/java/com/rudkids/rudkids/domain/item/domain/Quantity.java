package com.rudkids.rudkids.domain.item.domain;

import com.rudkids.rudkids.domain.item.exception.InvalidItemQuantityException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Quantity {
    private static final int MIN = 0;

    @Column(name = "quantity")
    private int value;

    protected Quantity() {
    }

    private Quantity(int value) {
        this.value = value;
    }

    public static Quantity create(int value) {
        validate(value);
        return new Quantity(value);
    }

    private static void validate(int value) {
        if(value < MIN) {
            throw new InvalidItemQuantityException();
        }
    }
}
