package com.rudkids.rudkids.domain.product;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class ProductInfo {

    @Builder
    public record Main(
        UUID productId,
        String title,
        String frontImageUrl,
        String backImageUrl,
        ProductStatus status
    ) {
    }

    @Builder
    public record Detail(
        String title,
        String bio,
        String frontImageUrl,
        String backImageUrl,
        List<ProductItem> items
    ) {
    }

    @Builder
    public record ProductItem(
        UUID itemId,
        String name,
        int price,
        ItemStatus itemStatus
    ) {
    }
}
