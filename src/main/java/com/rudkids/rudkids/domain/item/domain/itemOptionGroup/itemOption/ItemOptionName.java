package com.rudkids.rudkids.domain.item.domain.itemOptionGroup.itemOption;

import com.rudkids.rudkids.domain.item.exception.InvalidItemNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class ItemOptionName {
    private static final int MAX_LENGTH = 20;

    @Column(name = "name", unique = true)
    private String value;

    protected ItemOptionName() {
    }

    private ItemOptionName(String value) {
        this.value = value;
    }

    public static ItemOptionName create(String value) {
        validate(value);
        return new ItemOptionName(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidItemNameException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidItemNameException();
        }
    }
}
