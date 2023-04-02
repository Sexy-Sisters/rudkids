package com.rudkids.rudkids.interfaces.product.dto;

import com.rudkids.rudkids.domain.product.ProductCommand;
import com.rudkids.rudkids.domain.product.ProductInfo;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    public ProductCommand.RegisterRequest to(ProductRequest.Register request) {
        return ProductCommand.RegisterRequest.builder()
            .title(request.title())
            .productBio(request.productBio())
            .build();
    }

    public ProductResponse.Main to(ProductInfo.Main info) {
        return ProductResponse.Main.builder()
            .productId(info.productId())
            .title(info.title())
            .productBio(info.productBio())
            .build();
    }
}
