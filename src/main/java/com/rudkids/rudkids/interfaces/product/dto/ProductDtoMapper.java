package com.rudkids.rudkids.interfaces.product.dto;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    public ProductCommand.CreateRequest toCommand(ProductRequest.Create request) {
        return new ProductCommand.CreateRequest(request.title(), request.productBio());
    }

    public ProductCommand.UpdateRequest toCommand(ProductRequest.Update request) {
        return new ProductCommand.UpdateRequest(request.title(), request.productBio());
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
