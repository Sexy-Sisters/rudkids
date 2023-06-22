package com.rudkids.core.item.domain;

import com.rudkids.core.item.exception.InvalidItemNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {
    private static final int MAX_LENGTH = 20;

    @Column(name = "enName", unique = true)
    private String enName;

    @Column(name = "koName", unique = true)
    private String koName;

    public Name(String enName, String koName) {
        this.enName = enName;
        this.koName = koName;
    }

    public static Name create(String enName, String koName) {
        validate(enName);
        validate(koName);
        return new Name(enName, koName);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidItemNameException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidItemNameException();
        }
    }
}
