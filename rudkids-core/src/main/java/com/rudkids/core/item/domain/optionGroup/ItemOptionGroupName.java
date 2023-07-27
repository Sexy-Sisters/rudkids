package com.rudkids.core.item.domain.optionGroup;

import com.rudkids.core.item.exception.InvalidItemOptionGroupNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOptionGroupName {
    private static final int MAX_LENGTH = 20;

    @Column(name = "name")
    private String value;

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
