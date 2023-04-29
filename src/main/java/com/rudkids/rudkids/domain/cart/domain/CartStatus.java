package com.rudkids.rudkids.domain.cart.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CartStatus {
    ACTIVE("활성화"),
    INACTIVE("비활성화");
    private final String description;
}
