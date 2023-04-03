package com.rudkids.rudkids.interfaces.product.dto;

import lombok.Builder;

import java.util.UUID;

public class ProductResponse {

    @Builder
    public record Main(UUID productId, String title, String productBio) {
    }
}
