package com.rudkids.rudkids.domain.community.domain;

import com.rudkids.rudkids.domain.community.exception.InvalidCommunityTitleException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Title {
    private static final int MAX_LENGTH = 50;

    @Column(name = "title")
    private String value;

    protected Title() {
    }

    private Title(String value) {
        this.value = value;
    }

    public static Title create(String value) {
        validate(value);
        return new Title(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidCommunityTitleException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidCommunityTitleException();
        }
    }

    public String getValue() {
        return value;
    }
}
