package com.rudkids.core.product.domain;

import com.rudkids.core.product.exception.InvalidBioException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Bio {
    private static final int MAX_LENGTH = 1000;

    @Column(name = "bio")
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
        if(value == null || value.isBlank()) {
            throw new InvalidBioException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidBioException();
        }
    }
}
