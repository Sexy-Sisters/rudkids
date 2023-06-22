package com.rudkids.core.user.domain;

import com.rudkids.core.user.exception.InvalidNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserName {
    private static final int MAX_LENGTH = 20;

    @Column(name = "name")
    private String value;

    private UserName(String value) {
        this.value = value;
    }

    public static UserName create(String value) {
        validate(value);
        return new UserName(value);
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
