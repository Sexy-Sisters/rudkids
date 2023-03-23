package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidAgeRangeException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Age {
    private static final int MIN_AGE = 2;
    private static final int MAX_AGE = 100;

    @Column(name = "age")
    private int value;

    protected Age() {
    }

    private Age(int value) {
        this.value = value;
    }

    public static Age create(int value) {
        validate(value);
        return new Age(value);
    }

    private static void validate(int value) {
        if(value < MIN_AGE || value > MAX_AGE) {
            throw new InvalidAgeRangeException();
        }
    }

    public int getValue() {
        return value;
    }
}
