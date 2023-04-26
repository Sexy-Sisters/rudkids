package com.rudkids.rudkids.domain.product.domain;

import com.rudkids.rudkids.domain.product.exception.ProductStatusNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum ProductStatus {
    OPEN("OPEN"), CLOSED("CLOSED");
    private final String description;

    public static ProductStatus validate(ProductStatus target) {
        return Arrays.stream(values())
                .filter(status -> status.equals(target))
                .findFirst()
                .orElseThrow(ProductStatusNotFoundException::new);
    }
}
