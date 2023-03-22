package com.rudkids.rudkids.domain.product;

import lombok.Builder;
import lombok.Getter;

public class ProductCommand {

    @Getter
    @Builder
    public static class RegisterRequest {
        private String title;
        private String bio;
    }
}
