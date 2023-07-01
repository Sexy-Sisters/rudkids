package com.rudkids.core.user.domain;

import com.rudkids.core.user.exception.InvalidPhoneNumberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {
    private static final int MAX_LENGTH = 11;

    @Column(name = "phone_number")
    private String value;

    private PhoneNumber(String value) {
        this.value = value;
    }

    public static PhoneNumber create(String value) {
        validate(value);
        return new PhoneNumber(value);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidPhoneNumberException();
        }
        if (value.length() != MAX_LENGTH) {
            throw new InvalidPhoneNumberException();
        }
    }

    public boolean isEmpty() {
        return value == null;
    }
}