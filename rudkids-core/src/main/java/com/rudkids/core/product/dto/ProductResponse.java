package com.rudkids.core.product.dto;

import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.domain.MysteryProduct;
import com.rudkids.core.product.domain.Product;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.UUID;

public class ProductResponse {

    public record Main(
        UUID productId,
        String title,
        String frontImageUrl,
        String backImageUrl
    ) {
        public Main(Product product) {
            this(product.getId(), product.getTitle(), product.getFrontImageUrl(), product.getBackImageUrl());
        }

        public Main(MysteryProduct product) {
            this(product.getId(), product.getTitle(), product.getFrontImageUrl(), product.getBackImageUrl());
        }
    }

    @Builder
    public record Detail(
        String title,
        String bio,
        ImageResponse.Info frontImage,
        ImageResponse.Info backImage,
        ImageResponse.Info bannerImage,
        Page<ItemResponse.Main> items,
        ImageResponse.Info mobileImage
    ) {
        public Detail(Product product, Page<ItemResponse.Main> items) {
            this(
                product.getTitle(),
                product.getBio(),
                new ImageResponse.Info(product.getFrontImagePath(), product.getFrontImageUrl()),
                new ImageResponse.Info(product.getBackImagePath(), product.getBackImageUrl()),
                new ImageResponse.Info(product.getBannerImagePath(), product.getBannerImageUrl()),
                items,
                new ImageResponse.Info(product.getMobileBannerImagePath(), product.getMobileBannerImageUrl())
            );
        }

        public Detail(MysteryProduct product, Page<ItemResponse.Main> items) {
            this(
                product.getTitle(),
                product.getBio(),
                new ImageResponse.Info(product.getFrontImagePath(), product.getFrontImageUrl()),
                new ImageResponse.Info(product.getBackImagePath(), product.getBackImageUrl()),
                new ImageResponse.Info(product.getBannerImagePath(), product.getBannerImageUrl()),
                items,
                new ImageResponse.Info(product.getMobileBannerImagePath(), product.getMobileBannerImageUrl())
            );
        }
    }
}
