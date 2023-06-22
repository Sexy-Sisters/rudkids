package com.rudkids.core.community.domain;

import com.rudkids.core.community.exception.InvalidCommunityTitleException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Title {
    private static final int MAX_LENGTH = 50;

    @Column(name = "title")
    private String value;

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
}
