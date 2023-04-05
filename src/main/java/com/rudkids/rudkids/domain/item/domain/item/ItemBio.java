package com.rudkids.rudkids.domain.item.domain.item;

import com.rudkids.rudkids.domain.product.exception.InvalidBioException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ItemBio {
    private static final int MAX_LENGTH = 1000;

    @Column(name = "bio", unique = true)
    private String value;

    protected ItemBio() {
    }

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