package com.rudkids.rudkids.domain.product;

import lombok.Builder;

public class ProductCommand {

    @Builder
    public record RegisterRequest(
            String title,
            String bio) {
    };
}
