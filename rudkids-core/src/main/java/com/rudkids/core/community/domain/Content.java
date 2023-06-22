package com.rudkids.core.community.domain;

import com.rudkids.core.community.exception.InvalidCommunityContentException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {
    private static final int MAX_LENGTH = 10000;

    @Column(name = "content", columnDefinition = "TEXT")
    private String value;

    private Content(String value) {
        this.value = value;
    }

    public static Content create(String value) {
        validate(value);
        return new Content(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidCommunityContentException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidCommunityContentException();
        }
    }
}
