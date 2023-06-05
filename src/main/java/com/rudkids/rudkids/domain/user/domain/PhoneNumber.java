package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidPhoneNumberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
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

    public static PhoneNumber createDefault(String value) {
        if (value == null || value.isBlank()) {
            value =  "";
        }
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
}
