package com.rudkids.rudkids.domain.product;

import com.rudkids.rudkids.domain.item.ItemInfo;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class ProductInfo {

    @Builder
    public record Main(
        UUID productId,
        String title,
        String productBio
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
        String name,
        int price,
        ItemStatus itemStatus
    ) {
    }
}
