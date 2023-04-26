package com.rudkids.rudkids.domain.product;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public class ProductInfo {

    @Builder
    public record Main(
        UUID productId,
        String title,
        String productBio,
        String status
    ) {
    }

    @Builder
    public record Detail(
        String title,
        String bio,
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
