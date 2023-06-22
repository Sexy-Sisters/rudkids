package com.rudkids.core.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CartStatus {
    ACTIVE("활성화"),
    INACTIVE("비활성화");

    private final String description;
}
