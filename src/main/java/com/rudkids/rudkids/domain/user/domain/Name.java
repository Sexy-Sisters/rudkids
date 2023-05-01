package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {
    private static final int MAX_LENGTH = 20;

    @Column(name = "name")
    private String value;

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
        if(value.length() > MAX_LENGTH) {
            throw new InvalidNameException();
        }
    }
}
