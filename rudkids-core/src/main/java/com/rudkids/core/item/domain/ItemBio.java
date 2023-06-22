package com.rudkids.core.item.domain;

import com.rudkids.core.product.exception.InvalidBioException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemBio {
    private static final int MAX_LENGTH = 1000;

    @Column(name = "itemBio")
    private String value;

    private ItemBio(String value) {
        this.value = value;
    }

    public static ItemBio create(String value) {
        validate(value);
        return new ItemBio(value);
    }

    private static void validate(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidBioException();
        }
        if(value.length() > MAX_LENGTH) {
            throw new InvalidBioException();
        }
    }
}