package com.rudkids.rudkids.domain.product;

import com.rudkids.rudkids.domain.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductInfo.Main of(Product product) {
        return ProductInfo.Main.builder()
            .productId(product.getId())
            .title(product.getTitle())
            .bio(product.getProductBio())
            .build();
    }
}
