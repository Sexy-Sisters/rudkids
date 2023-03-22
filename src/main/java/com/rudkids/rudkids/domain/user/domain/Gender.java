package com.rudkids.rudkids.domain.user.domain;

import com.rudkids.rudkids.domain.user.exception.InvalidGenderException;

import java.util.Arrays;

public enum Gender {
    MAIL("MALE"), FEMALE("FEMALE");

    private String name;

    Gender() {
    }

    Gender(String name) {
        this.name = name;
    }

    public static Gender toEnum(String name) {
        return Arrays.stream(Gender.values())
                .filter(direction -> direction.name.equals(name))
                .findAny()
                .orElseThrow(InvalidGenderException::new);
    }
}
