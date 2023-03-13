package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidEmailFormatException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.regex.Pattern;

@Embeddable
@Getter
public class Email {
    private static final Pattern PATTERN = Pattern.compile("\\\\w+@\\\\w+\\\\.\\\\w+(\\\\.\\\\w+)?");

    @Column(name = "email", unique = true)
    private String value;

    protected Email() {
    }

    private Email(String value) {
        this.value = value;
    }

    public static Email create(String value) {
        validate(value);
        return new Email(value);
    }

    private static void validate(String value) {
        if(!PATTERN.matcher(value).matches()) {
            throw new InvalidEmailFormatException();
        }
    }
}
