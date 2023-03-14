package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidPhoneNumberFormatException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.regex.Pattern;

@Embeddable
public class PhoneNumber {
    private static final Pattern PATTERN = Pattern.compile("^\\d{2,3}-\\d{3,4}-\\d{4}$");

    @Column(name = "phone_number")
    private String value;

    protected PhoneNumber() {
    }

    private PhoneNumber(String value) {
        this.value = value;
    }

    public static PhoneNumber create(String value) {
        validate(value);
        return new PhoneNumber(value);
    }

    private static void validate(String value) {
        if(!PATTERN.matcher(value).matches()) {
            throw new InvalidPhoneNumberFormatException();
        }
    }
}
