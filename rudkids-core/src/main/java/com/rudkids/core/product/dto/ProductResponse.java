package com.rudkids.core.product.dto;

import com.rudkids.core.image.dto.ImageResponse;
import com.rudkids.core.item.dto.ItemResponse;
import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductBannerImage;
import com.rudkids.core.product.domain.ProductStatus;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.Comparator;
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
        List<DetailBannerImage> bannerImages,
        Page<ItemResponse.Main> items,
        ImageResponse.Info mobileImage
    ) {
        public Detail(Product product, Page<ItemResponse.Main> items) {
            this(
                product.getTitle(),
                product.getProductBio(),
                new ImageResponse.Info(product.getFrontImagePath(), product.getFrontImageUrl()),
                new ImageResponse.Info(product.getBackImagePath(), product.getBackImageUrl()),
                to(product),
                items,
                new ImageResponse.Info(product.getMobileImagePath(), product.getMobileImageUrl())
            );
        }

        private static List<DetailBannerImage> to(Product product) {
            return product.getProductBannerImages().stream()
                .sorted(Comparator.comparing(ProductBannerImage::getOrdering))
                .map(ProductResponse.DetailBannerImage::new)
                .toList();
        }
    }

    public record DetailBannerImage(
        UUID id,
        String path,
        String url,
        int ordering
    ) {
        public DetailBannerImage(ProductBannerImage image) {
            this(image.getId(), image.getPath(), image.getUrl(), image.getOrdering());
        }
    }
}
