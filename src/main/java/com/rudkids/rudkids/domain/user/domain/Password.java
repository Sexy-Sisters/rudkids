package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidPasswordException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public class Password {
    private static final Pattern PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}$");

    @Column(name = "password", unique = true)
    private String value;

    protected Password() {
    }

    private Password(String value) {
        this.value = value;
    }

    public static Password create(String value) {
        validate(value);
        return new Password(value);
    }

    private static void validate(String value) {
        if(!PATTERN.matcher(value).matches()) {
            throw new InvalidPasswordException();
        }
    }
}
