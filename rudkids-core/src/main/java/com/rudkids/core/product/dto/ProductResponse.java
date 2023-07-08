package com.rudkids.core.product.dto;

import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductStatus;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public class ProductResponse {

    public record Main(
        UUID productId,
        String title,
        String frontImageUrl,
        String backImageUrl,
        ProductStatus status
    ) {
        public Main(Product product) {
            this(
                product.getId(),
                product.getTitle(),
                product.getFrontImageUrl(),
                product.getBackImageUrl(),
                product.getProductStatus()
            );
        }
    }

    @Builder
    public record Detail(
        String title,
        String bio,
        ImageResponse.Info frontImage,
        ImageResponse.Info backImage,
        List<ImageResponse.Info> bannerImages,
        Page<ItemResponse.Main> items
    ) {}
}
