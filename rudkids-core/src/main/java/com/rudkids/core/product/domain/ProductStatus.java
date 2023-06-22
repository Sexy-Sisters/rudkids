package com.rudkids.core.product.domain;

import com.rudkids.core.product.exception.ProductStatusNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum ProductStatus {
    OPEN("진행중"),
    CLOSED("중단");

    private final String description;

    public static ProductStatus toEnum(String description) {
        return Arrays.stream(values())
            .filter(it -> it.isSameDescription(description))
            .findFirst()
            .orElseThrow(ProductStatusNotFoundException::new);
    }

    private boolean isSameDescription(String description) {
        return this.name().equals(description);
    }
}
