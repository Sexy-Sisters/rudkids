package com.rudkids.core.video.domain;

import com.rudkids.core.video.exception.InvalidVideoBioException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoBio {
    private static final int MAX_LENGTH = 1000;

    @Column(name = "bio")
    private String value;

    private VideoBio(String value) {
        this.value = value;
    }

    public static VideoBio create(String value) {
        validate(value);
        return new VideoBio(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidVideoBioException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidVideoBioException();
        }
    }
}
