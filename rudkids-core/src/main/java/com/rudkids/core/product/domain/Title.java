package com.rudkids.core.product.domain;

import com.rudkids.core.product.exception.InvalidProductTitleException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Title {
    private static final int MAX_LENGTH = 20;

    @Column(name = "title", unique = true)
    private String value;

    protected Title() {
    }

    private Title(String value) {
        this.value = value;
    }

    public static Title create(String value) {
        validate(value);
        return new Title(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidProductTitleException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidProductTitleException();
        }
    }
}
