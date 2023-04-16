package com.rudkids.rudkids.domain.magazine.domain;

import com.rudkids.rudkids.domain.magazine.exception.InvalidMagazineContentException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Content {
    private static final int MAX_LENGTH = 10000;

    @Column(name = "content", columnDefinition = "TEXT")
    private String value;

    protected Content() {
    }

    private Content(String value) {
        this.value = value;
    }

    public static Content create(String value) {
        validate(value);
        return new Content(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidMagazineContentException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidMagazineContentException();
        }
    }

    public String getValue() {
        return value;
    }
}
