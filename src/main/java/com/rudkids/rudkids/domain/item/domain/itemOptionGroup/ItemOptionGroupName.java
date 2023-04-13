package com.rudkids.rudkids.domain.item.domain.itemOptionGroup;

import com.rudkids.rudkids.domain.item.exception.InvalidItemNameException;
import com.rudkids.rudkids.domain.item.exception.InvalidItemOptionGroupNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class ItemOptionGroupName {
    private static final int MAX_LENGTH = 20;

    @Column(name = "name", unique = true)
    private String value;

    protected ItemOptionGroupName() {
    }

    private ItemOptionGroupName(String value) {
        this.value = value;
    }

    public static ItemOptionGroupName create(String value) {
        validate(value);
        return new ItemOptionGroupName(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidItemOptionGroupNameException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidItemOptionGroupNameException();
        }
    }
}
