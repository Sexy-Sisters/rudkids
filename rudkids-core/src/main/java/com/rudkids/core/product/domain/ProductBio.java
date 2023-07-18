package com.rudkids.core.product.domain;

import com.rudkids.core.product.exception.InvalidBioException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class ProductBio {
    private static final int MAX_LENGTH = 1000;

    @Column(name = "bio")
    private String value;

    protected ProductBio() {
    }

    private ProductBio(String value) {
        this.value = value;
    }

    public static ProductBio create(String value) {
        validate(value);
        return new ProductBio(value);
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
