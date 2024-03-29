package com.rudkids.core.item.domain;

import com.rudkids.core.item.exception.InvalidItemPriceException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Price {
    private static final int MIN_PRICE = 0;

    @Column(name = "price")
    private int value;

    protected Price() {
    }

    private Price(int value) {
        this.value = value;
    }

    public static Price create(int value) {
        validate(value);
        return new Price(value);
    }

    private static void validate(int value) {
        if(value < MIN_PRICE) {
            throw new InvalidItemPriceException();
        }
    }
}
