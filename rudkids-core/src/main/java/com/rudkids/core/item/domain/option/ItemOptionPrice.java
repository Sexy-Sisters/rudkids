package com.rudkids.core.item.domain.option;

import com.rudkids.core.item.exception.InvalidItemOptionPriceException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class ItemOptionPrice {
    private static final int MIN_PRICE = 0;

    @Column(name = "price")
    private int value;

    protected ItemOptionPrice() {
    }

    private ItemOptionPrice(int value) {
        this.value = value;
    }

    public static ItemOptionPrice create(int value) {
        validate(value);
        return new ItemOptionPrice(value);
    }

    private static void validate(int value) {
        if(value < MIN_PRICE) {
            throw new InvalidItemOptionPriceException();
        }
    }
}
