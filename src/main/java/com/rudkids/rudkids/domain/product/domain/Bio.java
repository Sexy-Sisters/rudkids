package com.rudkids.rudkids.domain.product.domain;

import com.rudkids.rudkids.domain.product.exception.InvalidBioException;
import com.rudkids.rudkids.domain.product.exception.InvalidProductTitleException;
import jakarta.persistence.Column;

public class Bio {
    private static final int MAX_LENGTH = 1000;

    @Column(name = "title", unique = true)
    private String value;

    protected Bio() {
    }

    private Bio(String value) {
        this.value = value;
    }

    public static Bio create(String value) {
        validate(value);
        return new Bio(value);
    }

    private static void validate(String value) {
        if(value.isBlank()) {
            throw new InvalidProductTitleException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidBioException();
        }
    }
}
