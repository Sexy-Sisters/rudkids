package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidGenderException;

import java.util.Arrays;

public enum Gender {
    MAIL("MALE"), FEMALE("FEMALE");

    private String description;

    Gender() {
    }

    Gender(String description) {
        this.description = description;
    }

    public static Gender toEnum(String description) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.description.equals(description))
                .findAny()
                .orElseThrow(InvalidGenderException::new);
    }
}
