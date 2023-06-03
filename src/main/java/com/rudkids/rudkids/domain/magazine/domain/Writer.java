package com.rudkids.rudkids.domain.magazine.domain;

import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineWriterException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Writer {
    private static final int MAX_LENGTH = 10;

    @Column(name = "writer")
    private String value;

    protected Writer() {
    }

    private Writer(String value) {
        this.value = value;
    }

    public static Writer create(String value) {
        validate(value);
        return new Writer(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidMagazineWriterException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidMagazineWriterException();
        }
    }

    public String getValue() {
        return value;
    }
}
