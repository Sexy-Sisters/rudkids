package com.rudkids.rudkids.interfaces.product.dto;

import com.rudkids.rudkids.domain.product.domain.ProductStatus;

public class ProductRequest {

    public record Create(String title, String productBio) {
    }

    public record Update(String title, String productBio) {
    }

    public record ChangeStatus(ProductStatus productStatus) {
    }
}
