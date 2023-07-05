package com.rudkids.core.product.domain;

import com.rudkids.core.product.exception.ProductCategoryNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum ProductCategory {
    TOY("장난감"),
    FLYER("파리"),
    STICKER("스티커");

    private final String description;

    public static ProductCategory toEnum(String description) {
        return Arrays.stream(values())
            .filter(it -> it.isSameDescription(description))
            .findFirst()
            .orElseThrow(ProductCategoryNotFoundException::new);
    }

    private boolean isSameDescription(String description) {
        return name().equals(description);
    }
}
