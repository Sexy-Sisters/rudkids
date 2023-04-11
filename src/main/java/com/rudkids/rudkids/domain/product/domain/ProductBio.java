package com.rudkids.rudkids.domain.product.domain;

import com.rudkids.rudkids.domain.product.exception.InvalidBioException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ProductBio {
    private static final int MAX_LENGTH = 1000;

    @Column(name = "itemBio")
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
