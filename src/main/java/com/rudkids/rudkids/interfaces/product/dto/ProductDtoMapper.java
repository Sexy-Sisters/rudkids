package com.rudkids.rudkids.interfaces.product.dto;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    public ProductCommand.CreateRequest toCommand(ProductRequest.Create request) {
        return ProductCommand.CreateRequest.builder()
            .title(request.title())
            .productBio(request.productBio())
            .frontImage(request.frontImage())
            .backImage(request.backImage())
            .build();
    }

    public ProductCommand.UpdateRequest toCommand(ProductRequest.Update request) {
        return new ProductCommand.UpdateRequest(
            request.title(),
            request.productBio(),
            request.frontImage(),
            request.backImage()
        );
    }

    public ProductResponse.Main toResponse(ProductInfo.Main info) {
        return ProductResponse.Main.builder()
            .productId(info.productId())
            .title(info.title())
            .frontImageUrl(info.frontImageUrl())
            .backImageUrl(info.backImageUrl())
            .status(info.status())
            .build();
    }

    public ProductResponse.Detail toResponse(ProductInfo.Detail info) {
        var items = info.items().stream()
            .map(this::toResponse)
            .toList();

        return ProductResponse.Detail.builder()
            .title(info.title())
            .bio(info.bio())
            .frontImageUrl(info.frontImageUrl())
            .backImageUrl(info.backImageUrl())
            .items(items)
            .build();
    }

    private ProductResponse.ProductItem toResponse(ProductInfo.ProductItem info) {
        return ProductResponse.ProductItem.builder()
            .itemId(info.itemId())
            .name(info.name())
            .price(info.price())
            .imageUrls(info.imageUrls())
            .itemStatus(info.itemStatus())
            .build();
    }
}
