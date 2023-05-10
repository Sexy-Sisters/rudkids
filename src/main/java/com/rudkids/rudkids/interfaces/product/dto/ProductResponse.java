package com.rudkids.rudkids.interfaces.product.dto;

import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.product.domain.ProductStatus;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public class ProductResponse {

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
        Page<ProductResponse.ProductItem> items
    ) {}

    @Builder
    public record ProductItem(
        UUID itemId,
        String name,
        int price,
        List<String> imageUrls,
        ItemStatus itemStatus
    ) {
    }
}
