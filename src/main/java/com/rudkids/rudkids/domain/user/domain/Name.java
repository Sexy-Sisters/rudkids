package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidEmailFormatException;
import com.rudkids.rudkids.domain.user.exception.InvalidUserNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Name {
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
        if(value.isBlank()) {
            throw new InvalidEmailFormatException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidUserNameException();
        }
    }
}
