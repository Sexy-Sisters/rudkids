package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public class Name {
    private static final Pattern PATTERN = Pattern.compile("^{2,20}$");

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
        if(!PATTERN.matcher(value).matches()) {
            throw new InvalidNameException();
        }
    }
}
