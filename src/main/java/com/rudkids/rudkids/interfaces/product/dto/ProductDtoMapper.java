package com.rudkids.rudkids.interfaces.product.dto;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    public ProductCommand.RegisterRequest toCommand(ProductRequest.Register request) {
        return ProductCommand.RegisterRequest.builder()
            .title(request.title())
            .productBio(request.productBio())
            .build();
    }

    public ProductResponse.Main toResponse(ProductInfo.Main info) {
        return ProductResponse.Main.builder()
            .productId(info.productId())
            .title(info.title())
            .productBio(info.productBio())
            .build();
    }

    public ProductResponse.Detail toResponse(ProductInfo.Detail info) {
        var items = info.items().stream()
            .map(this::toResponse)
            .toList();

        return ProductResponse.Detail.builder()
            .title(info.title())
            .bio(info.bio())
            .items(items)
            .build();
    }

    private ProductResponse.ProductItem toResponse(ProductInfo.ProductItem info) {
        return ProductResponse.ProductItem.builder()
            .itemId(info.itemId())
            .name(info.name())
            .price(info.price())
            .itemStatus(info.itemStatus())
            .build();
    }
}
