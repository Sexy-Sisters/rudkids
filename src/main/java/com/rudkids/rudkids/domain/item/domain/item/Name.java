package com.rudkids.rudkids.domain.item.domain.item;

import com.rudkids.rudkids.domain.item.exception.InvalidItemNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Name {
    private static final int MAX_LENGTH = 20;

    @Column(name = "name", unique = true)
    private String value;

    protected Name() {
    }

    private Name(String value) {
        this.value = value;
    }

    public static Name create(String value) {
        validate(value);
        return new Name(value);
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
