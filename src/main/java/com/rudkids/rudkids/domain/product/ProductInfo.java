package com.rudkids.rudkids.domain.product;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class ProductInfo {

    @Builder
    public record Main(UUID productId, String title, String bio) {
    }
}
