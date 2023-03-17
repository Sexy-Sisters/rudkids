package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public class Name {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 20;

    @Column(name = "name")
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
            throw new InvalidNameException();
        }
        if(MIN_LENGTH > value.length() || MAX_LENGTH < value.length()) {
            throw new InvalidNameException();
        }
    }
}
