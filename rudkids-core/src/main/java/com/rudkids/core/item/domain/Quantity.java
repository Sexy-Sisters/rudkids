package com.rudkids.core.item.domain;

import com.rudkids.core.item.exception.InvalidItemQuantityException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quantity {
    private static final int MIN_AMOUNT = 0;

    @Column(name = "quantity")
    private int value;

    private Quantity(int value) {
        this.value = value;
    }

    public static Quantity create(int value) {
        validate(value);
        return new Quantity(value);
    }

    private static void validate(int value) {
        if(value < MIN_AMOUNT) {
            throw new InvalidItemQuantityException();
        }
    }
}
