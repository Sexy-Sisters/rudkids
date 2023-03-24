package com.rudkids.rudkids.domain.product;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class ProductInfo {

    @Getter
    @Builder
    public static class Main {
        private UUID productId;
        private String title;
        private String bio;
    }
}
