package com.rudkids.rudkids.interfaces.product.dto;

import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.interfaces.image.dto.ImageRequest;

public class ProductRequest {

    public record Create(
        String title,
        String productBio,
        ImageRequest frontImage,
        ImageRequest backImage
    ) {
    }

    public record Update(
        String title,
        String productBio,
        ImageRequest frontImage,
        ImageRequest backImage
    ) {
    }

    public record ChangeStatus(ProductStatus productStatus) {
    }
}