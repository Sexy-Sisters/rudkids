package com.rudkids.rudkids.interfaces.product.dto;

import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import com.rudkids.rudkids.interfaces.image.dto.ImageRequest;

import java.util.List;

public class ProductRequest {

    public record Create(
        String title,
        String productBio,
        ImageRequest frontImage,
        ImageRequest backImage,
        List<ImageRequest> bannerImages
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